package com.example.demo.dto.playlist;

public class PlaylistDTO {

    private Long id;
    private String name;
    private String description;
    private boolean isPublic;
    private String ownerName;

    public PlaylistDTO() {}

    public PlaylistDTO(Long id, String name, String description, boolean isPublic, String ownerName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.ownerName = ownerName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}
