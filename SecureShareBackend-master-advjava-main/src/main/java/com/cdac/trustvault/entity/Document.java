package com.cdac.trustvault.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


	@Entity
	@Table(name = "documents")
	@Data 
//	@NoArgsConstructor 
//	@AllArgsConstructor
public class Document {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Long size;

    private LocalDateTime uploadedAt;

    @Lob
    private byte[] encryptedContent;

    private String encryptionKey;

    private String uploadedBy;
    
    private String uploaderEmail;
    
    @Transient
    private boolean isShared;
    @Transient
    private String sharedBy;
    @Transient
    private String sharedWith;
    @Transient
    private LocalDateTime sharedAt;
    
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(Long id, String name, String type, Long size, LocalDateTime uploadedAt, byte[] encryptedContent,
			String encryptionKey, String uploadedBy, String uploaderEmail, boolean isShared, String sharedBy,
			String sharedWith, LocalDateTime sharedAt) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.uploadedAt = uploadedAt;
		this.encryptedContent = encryptedContent;
		this.encryptionKey = encryptionKey;
		this.uploadedBy = uploadedBy;
		this.uploaderEmail = uploaderEmail;
		this.isShared = isShared;
		this.sharedBy = sharedBy;
		this.sharedWith = sharedWith;
		this.sharedAt = sharedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public byte[] getEncryptedContent() {
		return encryptedContent;
	}

	public void setEncryptedContent(byte[] encryptedContent) {
		this.encryptedContent = encryptedContent;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getUploaderEmail() {
		return uploaderEmail;
	}

	public void setUploaderEmail(String uploaderEmail) {
		this.uploaderEmail = uploaderEmail;
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public String getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(String sharedBy) {
		this.sharedBy = sharedBy;
	}

	public String getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(String sharedWith) {
		this.sharedWith = sharedWith;
	}

	public LocalDateTime getSharedAt() {
		return sharedAt;
	}

	public void setSharedAt(LocalDateTime sharedAt) {
		this.sharedAt = sharedAt;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + ", type=" + type + ", size=" + size + ", uploadedAt="
				+ uploadedAt + ", encryptedContent=" + Arrays.toString(encryptedContent) + ", encryptionKey="
				+ encryptionKey + ", uploadedBy=" + uploadedBy + ", uploaderEmail=" + uploaderEmail + ", isShared="
				+ isShared + ", sharedBy=" + sharedBy + ", sharedWith=" + sharedWith + ", sharedAt=" + sharedAt + "]";
	}
	
    
    
}
