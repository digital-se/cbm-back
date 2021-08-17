package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Bucket;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.responseFiles.RFArquivo;
import com.digitalse.cbm.back.responseFiles.RFBucket;
import com.digitalse.cbm.back.responseFiles.RFCriarArquivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BucketRepository bucketRepository;

    /**
     * Adiciona varios arquivos simultaneamente a um documento.
     * @param documento_id id do documento no qual serão adicionados os arquivos.
     * @param arquivosRF Lista de DTOs de arquivo com suas informações básicas.
     * @param files Lista de MultipartFiles (imagens) para cada DTO.
     * @return retorna uma lista de responde files com os arquivos adicionados.
     * @throws IOException
     */
    public List<RFCriarArquivo> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosRF,
            LinkedList<MultipartFile> files) throws IOException {
        List<RFCriarArquivo> returnRF = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();

        while (!arquivosRF.isEmpty()) {
            ArquivoDTO tempArq = arquivosRF.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            Bucket bucket = new Bucket(null, tempFile.getOriginalFilename(), tempFile.getContentType(),
                    tempFile.getSize(), tempFile.getBytes(), null, null);
            bucket = bucketRepository.save(bucket);

            Arquivo finalArq = new Arquivo(tempArq.getId(), doc, tempFile.getOriginalFilename(), tempArq.getOcr(),
                    tempArq.getStatus(), tempArq.getTexto(), bucket.getId(), null,
                    null);
            RFCriarArquivo rfarq = new RFCriarArquivo(arquivoRepository.save(finalArq));
            returnRF.add(rfarq);
        }

        return returnRF;
    }
    /**
     * Busca um arquivo e retorna a imagem do mesmo que está contida no bucket.
     * @param arquivo_id id do arquivo para retornar a imagem do mesmo.
     * @return uma imagem.
     */
    public RFBucket getBucket(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        Bucket bucket = bucketRepository.findById(arq.getBucket()).get();
        return new RFBucket(bucket);
    }

    /**
     * Busca um arquivo e retorna suas informações exceto os dados (bytes).
     * @param arquivo_id id do arquivo para retornar suas informações.
     * @return um responsefile com as informações do arquivo.
     */
    public RFArquivo getArquivo(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        return new RFArquivo(arq);
    }

    /**
     * Busca todos os arquivos de um documento.
     * @param documento_id id do documento no qual se buscará os arquivos.
     * @return retorna uma lista com response files dos arquivos contidos no documento.
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
     * Método incompleto no momento!!!
     * @param id
     * @param newArquivo
     * @return
     * @throws IOException
     */
    public RFArquivo editar(long id, ArquivoDTO newArquivo/* , MultipartFile newFile */) throws IOException {
        Arquivo arq = arquivoRepository.findById(id).get();
        arq.setOcr(newArquivo.getOcr());
        arq.setStatus(newArquivo.getStatus());
        
        arq.setTexto(newArquivo.getTexto());
        arq.setAtualizado(OffsetDateTime.now());
        arquivoRepository.save(arq);
        return new RFArquivo(arq);
    }

    public void deletarArquivo(long id) {
        arquivoRepository.deleteById(id);
    }
}
