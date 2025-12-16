package com.platform.libraryManager.services;

import com.platform.libraryManager.factories.ResourceFactory;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.payloads.resource.EditResourcePayload;
import com.platform.libraryManager.providers.FilebaseIPFSProvider;
import com.platform.libraryManager.repositories.ResourceRepository;
import com.platform.libraryManager.responses.endpoints.resource.add.AddResourceErrorResponse;
import com.platform.libraryManager.responses.endpoints.resource.add.AddResourceResponse;
import com.platform.libraryManager.responses.endpoints.resource.add.AddResourceSuccessResponse;
import com.platform.libraryManager.responses.endpoints.resource.edit.EditResourceErrorResponse;
import com.platform.libraryManager.responses.endpoints.resource.edit.EditResourceResponse;
import com.platform.libraryManager.responses.endpoints.resource.edit.EditResourceSuccessResponse;
import com.platform.libraryManager.responses.endpoints.resource.getUnique.GetUniqueResourceErrorResponse;
import com.platform.libraryManager.responses.endpoints.resource.getUnique.GetUniqueResourceResponse;
import com.platform.libraryManager.responses.endpoints.resource.getUnique.GetUniqueResourceSuccessResponse;
import com.platform.libraryManager.responses.endpoints.resource.remove.RemoveResourceErrorResponse;
import com.platform.libraryManager.responses.endpoints.resource.remove.RemoveResourceResponse;
import com.platform.libraryManager.responses.endpoints.resource.remove.RemoveResourceSuccessResponse;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import com.platform.libraryManager.searchQueryParams.ResourceSearchQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResourceService {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private FilebaseIPFSProvider filebaseIPFSProvider;

    public AddResourceResponse addResource(
            MultipartFile file,
            AddResourcePayload addResourcePayload) {

        try {

            final Resource resource = ResourceFactory.create(
                    addResourcePayload,
                    libraryService.getUniqueLibrary(
                            new LibrarySearchQueryParams(addResourcePayload.getLibraryId(), null, null, null, null))
                            .getLibrary());

            resource.setLink(filebaseIPFSProvider.uploadFile(file));
            resourceRepository.save(resource);
            return new AddResourceSuccessResponse();

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return new AddResourceErrorResponse(409, "Resource already exists");

        } catch (Exception exception) {
            return new AddResourceErrorResponse(400, "Error");

        }
    }

    public EditResourceResponse editResource(Long id, EditResourcePayload editResourcePayload) {

        try {

            final Resource oldResource = resourceRepository.findById(id).get();

            final Resource resource = ResourceFactory.create(
                    id,
                    editResourcePayload,
                    libraryService.getUniqueLibrary(
                            new LibrarySearchQueryParams(editResourcePayload.getLibraryId(), null, null, null, null))
                            .getLibrary());

            resource.setLink(oldResource.getLink());
            resource.setStatus(oldResource.getStatus());
            resourceRepository.save(resource);
            return new EditResourceSuccessResponse();

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return new EditResourceErrorResponse(409, "A Resource with the same data already exists");

        } catch (Exception exception) {
            return new EditResourceErrorResponse(400, "Error");

        }
    }

    public RemoveResourceResponse removeResource(Long id) {

        try {
            final Resource resource = getUniqueResource(
                    new ResourceSearchQueryParams(id, null, null, null, null, null, null, null, null)).getResource();

            resourceRepository.delete(resource);
            return new RemoveResourceSuccessResponse();

        } catch (Exception exception) {
            return new RemoveResourceErrorResponse(400, "Error");
        }
    }

    public void getAllResources() {
    }

    public GetUniqueResourceResponse getUniqueResource(ResourceSearchQueryParams resourceSearchQueryParams) {

        try {
            final Resource resource = resourceRepository.searchOne(resourceSearchQueryParams).get();
            return new GetUniqueResourceSuccessResponse(resource);

        } catch (Exception exception) {
            return new GetUniqueResourceErrorResponse();
        }
    }
}
