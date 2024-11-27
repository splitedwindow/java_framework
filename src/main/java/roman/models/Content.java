package roman.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private int contentId;

    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id")
    private File file;  // Many content entries can belong to one file

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType; // Enum for content type ('text' or 'image')

    @Column(name = "content_text")
    private String contentText; // For 'text' content type, stores the text content

    @Column(name = "content_url")
    private String contentUrl; // For 'image' content type, stores the image URL

    @Column(name = "sequence", nullable = false)
    private int sequence; // Sequence of content within the file

    // Enum to define content types
    public enum ContentType {
        text, image
    }

    // Getters and Setters
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    // Override toString(), equals(), and hashCode() if necessary
}

