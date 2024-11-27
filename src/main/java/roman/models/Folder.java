package roman.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Folders")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Integer folderId;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id", referencedColumnName = "folder_id")
    private Folder parentFolder;

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> subFolders;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructors, Getters, Setters

    public Folder() {}

    public Folder(String name, Folder parentFolder) {
        this.name = name;
        this.parentFolder = parentFolder;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

