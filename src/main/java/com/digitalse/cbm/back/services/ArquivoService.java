package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoEditarDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Bucket;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFArquivo;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFCriarArquivo;
import com.digitalse.cbm.back.responseFiles.RFsBucket.RFBucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private OcrService ocrService;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BucketRepository bucketRepository;

    /**
     * Adiciona um arquivo a um documento.
     * 
     * @param documento_id id do documento no qual sera adicionado o arquivo.
     * @param arquivosList arquivo com suas informações básicas.
     * @param filesList    MultipartFile (imagens) para DTO.
     * @return retorna um response files com o arquivo adicionado.
     * @throws IOException
     */
    public RFCriarArquivo addArchive(long documento_id, ArquivoDTO arquivodto, MultipartFile file) throws IOException {

        Documento documento = documentoRepository.findById(documento_id).get();

        Bucket bucket = new Bucket(null, file.getOriginalFilename(), file.getContentType(), file.getSize(),
                file.getBytes(), null, null);
        bucket = bucketRepository.save(bucket);

        Arquivo finalArq = new Arquivo(null, documento, file.getOriginalFilename(), arquivodto.getOcr(), null, "",
                bucket.getId(), null, null);

        if (arquivodto.getOcr() == false)
            finalArq.setStatus("Concluido");
        if (arquivodto.getOcr() == true)
            finalArq.setStatus("Em pre-processamento");

        RFCriarArquivo responseFile = new RFCriarArquivo(arquivoRepository.save(finalArq));
        ocrService.updateOcr();
        return responseFile;
    }

    /**
     * Adiciona varios arquivos simultaneamente a um documento.
     * 
     * @param documento_id id do documento no qual serão adicionados os arquivos.
     * @param arquivosList Lista de DTOs de arquivo com suas informações básicas.
     * @param filesList    Lista de MultipartFiles (imagens) para cada DTO.
     * @return retorna uma lista de response files com os arquivos adicionados.
     * @throws IOException
     */
    public List<RFCriarArquivo> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosList,
            LinkedList<MultipartFile> filesList) throws IOException {
        List<RFCriarArquivo> returnRF = new ArrayList<>();
        Documento documento = documentoRepository.findById(documento_id).get();

        while (!arquivosList.isEmpty()) {
            ArquivoDTO arquivoBubble = arquivosList.removeFirst();
            MultipartFile fileBubble = filesList.removeFirst();

            Bucket bucket = new Bucket(null, fileBubble.getOriginalFilename(), fileBubble.getContentType(),
                    fileBubble.getSize(), fileBubble.getBytes(), null, null);
            bucket = bucketRepository.save(bucket);

            Arquivo finalArq = new Arquivo(0L, documento, fileBubble.getOriginalFilename(), arquivoBubble.getOcr(),
                    null, null, bucket.getId(), null, null);

            if (arquivoBubble.getOcr() == false)
                finalArq.setStatus("Concluido");
            if (arquivoBubble.getOcr() == true)
                finalArq.setStatus("Em pre-processamento");

            RFCriarArquivo responseFile = new RFCriarArquivo(arquivoRepository.save(finalArq));
            returnRF.add(responseFile);
        }
        ocrService.updateOcr();
        return returnRF;
    }

    /**
     * Busca um arquivo e retorna a imagem do mesmo que está contida no bucket.
     * 
     * @param arquivo_id id do arquivo para retornar a imagem do mesmo.
     * @return uma imagem.
     */
    public RFBucket getBucket(long arquivo_id) {
        Arquivo arquivo = arquivoRepository.findById(arquivo_id).get();
        Bucket bucket = bucketRepository.findById(arquivo.getBucket()).get();
        return new RFBucket(bucket);
    }

    /**
     * Busca um arquivo e retorna suas informações exceto os dados (bytes).
     * 
     * @param arquivo_id id do arquivo para retornar suas informações.
     * @return um responsefile com as informações do arquivo.
     */
    public RFArquivo getArquivo(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        return new RFArquivo(arq);
    }

    /**
     * Busca todos os arquivos de um documento.
     * 
     * @param documento_id id do documento no qual se buscará os arquivos.
     * @return retorna uma lista com response files dos arquivos contidos no
     *         documento.
     */
    public List<RFArquivo> getArchivesFromDocument(long documento_id) {
        List<RFArquivo> list = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();
        doc.getArquivos().forEach(arq -> {
            list.add(new RFArquivo(arq));
        });

        return list;
    }

    /**
     * Edita um documento, validando se foi enviado corretamente
     * 
     * @param arquivodto
     * @return
     * @throws IOException
     * @throws ValidationException
     */
    public RFArquivo editar(long documento_id, long arquivo_id, ArquivoEditarDTO arquivodto)
            throws IOException, ValidationException {
        if (!arquivodto.isValidationOk())
            throw new ValidationException("erro");

        Arquivo arq = arquivoRepository.findById(arquivo_id).get();

        if (arquivodto.getOcr() != arq.getOcr()){
            if (arquivodto.getOcr() == false){
                arq.setStatus("Concluido");
                arq.setTexto(null);
            } else if (arquivodto.getOcr() == true){
                arq.setStatus("Em pre-processamento");
            }
        } else if(arquivodto.getOcr() == true && arquivodto.getTexto() != null){
            arq.setTexto(arquivodto.getTexto());
        }

        arq.setOcr(arquivodto.getOcr());
        arquivoRepository.save(arq);
        return new RFArquivo(arq);
    }

    public void deletarArquivo(long id) {
        arquivoRepository.deleteById(id);
    }
}
