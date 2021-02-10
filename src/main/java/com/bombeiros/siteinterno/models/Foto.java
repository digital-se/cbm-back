import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_foto;

    private String name;

    private String type;
    
    @Lob
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "id_fichaFuncionario", referencedColumnName = "id_fichaFuncionario")
    private FichaFuncionario fichaFuncionario;

    public Foto() {

    }

    public Foto(String name, String type, byte[] foto) {
        this.name = name;
        this.type = type;
        this.foto = foto;
    }

    public Long getId_foto() {
        return id_foto;
    }

    public void setId_foto(Long id_foto) {
        this.id_foto = id_foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public FichaFuncionario getFichaFuncionario() {
        return fichaFuncionario;
    }

    public void setFichaFuncionario(FichaFuncionario fichaFuncionario) {
        this.fichaFuncionario = fichaFuncionario;
    }

    
    
}