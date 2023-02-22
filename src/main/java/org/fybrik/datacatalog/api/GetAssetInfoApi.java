/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.fybrik.datacatalog.api;

import org.fybrik.datacatalog.model.Connection;
import org.fybrik.datacatalog.model.GetAssetRequest;
import org.fybrik.datacatalog.model.GetAssetResponse;
import org.fybrik.datacatalog.model.ResourceColumn;
import org.fybrik.datacatalog.model.ResourceDetails;
import org.fybrik.datacatalog.model.ResourceMetadata;
import org.fybrik.datacatalog.model.S3;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "getAssetInfo", description = "the getAssetInfo API")
public interface GetAssetInfoApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /getAssetInfo : This REST API gets data asset information from the data catalog configured in fybrik for the data sets indicated in FybrikApplication yaml
     *
     * @param xRequestDatacatalogCred This header carries credential information related to relevant catalog from which the asset information needs to be retrieved. (required)
     * @param getAssetRequest Data Catalog Request Object. (required)
     * @return successful operation (status code 200)
     *         or Bad request - server cannot process the request due to client error (status code 400)
     */
    @Operation(
        operationId = "getAssetInfo",
        summary = "This REST API gets data asset information from the data catalog configured in fybrik for the data sets indicated in FybrikApplication yaml",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  GetAssetResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request - server cannot process the request due to client error")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/getAssetInfo",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<GetAssetResponse> getAssetInfo(
        @Parameter(name = "X-Request-Datacatalog-Cred", description = "This header carries credential information related to relevant catalog from which the asset information needs to be retrieved.", required = true, schema = @Schema(description = "")) @RequestHeader(value = "X-Request-Datacatalog-Cred", required = true) String xRequestDatacatalogCred,
        @Parameter(name = "GetAssetRequest", description = "Data Catalog Request Object.", required = true, schema = @Schema(description = "")) @Valid @RequestBody GetAssetRequest getAssetRequest
    ) {
        GetAssetResponse g = new GetAssetResponse();
        ResourceDetails rd = new ResourceDetails();
        ResourceMetadata rm = new ResourceMetadata();

        String credentials = "/v1/kubernetes-secrets/paysim-csv?namespace=fybrik-notebook-sample";
        
        Connection c = new Connection();
        S3 s3 = new S3();
        s3.endpoint("http://localstack.fybrik-notebook-sample.svc.cluster.local:4566");
        s3.bucket("demo");
        s3.objectKey("PS_20174392719_1491204439457_log.csv");

        c.setName("s3");
        c.setS3(s3);

        g.setCredentials(credentials);
        rd.setDataFormat("csv");
        rd.setConnection(c);
        g.setDetails(rd);

        rm.setName("Synthetic Financial Datasets For Fraud Detection");
        rm.setGeography("theshire");
        Map<String, Object> tags = new HashMap<String, Object>();
        tags.put("Purpose.finance", "true");
        rm.setTags(tags);

        List<ResourceColumn> columns = new ArrayList<ResourceColumn>();
        Map<String, Object> columnTags = new HashMap<String, Object>();
        columnTags.put("PII.Sensitive", "true");
        ResourceColumn rc1 = new ResourceColumn();
        ResourceColumn rc2 = new ResourceColumn();
        ResourceColumn rc3 = new ResourceColumn();
        rc1.setName("nameOrig");
        rc1.setTags(columnTags);
        rc2.setName("oldbalanceOrg");
        rc2.setTags(columnTags);
        rc3.setName("newbalanceOrig");
        rc3.setTags(columnTags);
        columns.add(rc1);
        columns.add(rc2);
        columns.add(rc3);

        rm.setColumns(columns);

        g.setResourceMetadata(rm);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(g);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    //String exampleString = "{ \"resourceMetadata\" : { \"owner\" : \"owner\", \"columns\" : [ { \"name\" : \"name\", \"tags\" : { \"region\" : \"region\", \"confidential\" : true } }, { \"name\" : \"name\", \"tags\" : { \"region\" : \"region\", \"confidential\" : true } } ], \"geography\" : \"geography\", \"name\" : \"name\", \"tags\" : { \"region\" : \"region\", \"confidential\" : true } }, \"credentials\" : \"credentials\", \"details\" : { \"connection\" : { \"additionalProperties\" : { \"datasourceType\" : \"datasourceType\", \"connectionString\" : \"connectionString\", \"connectionProperties\" : { \"s3\" : { \"bucket\" : \"bucket\", \"secret_key\" : \"secret_key\", \"endpoint\" : \"endpoint\", \"object_key\" : \"object_key\", \"api_key\" : \"api_key\", \"resource_instance_id\" : \"resource_instance_id\", \"access_key\" : \"access_key\", \"region\" : \"region\" }, \"db2\" : { \"database\" : \"database\", \"port\" : \"port\", \"ssl\" : \"ssl\", \"table\" : \"table\", \"url\" : \"url\" }, \"fybrik-arrow-flight\" : { \"hostname\" : \"hostname\", \"scheme\" : \"scheme\", \"port\" : \"port\" }, \"kafka\" : { \"schema_registry\" : \"schema_registry\", \"key_deserializer\" : \"key_deserializer\", \"ssl_truststore_password\" : \"ssl_truststore_password\", \"value_deserializer\" : \"value_deserializer\", \"sasl_mechanism\" : \"sasl_mechanism\", \"security_protocol\" : \"security_protocol\", \"topic_name\" : \"topic_name\", \"bootstrap_servers\" : \"bootstrap_servers\", \"ssl_truststore\" : \"ssl_truststore\" } }, \"assetProperties\" : [ { \"name\" : \"name\", \"value\" : \"value\" }, { \"name\" : \"name\", \"value\" : \"value\" } ], \"columns\" : [ { \"name\" : \"name\", \"type\" : { \"nullable\" : true, \"length\" : \"\", \"scale\" : \"\", \"signed\" : true, \"type\" : \"type\" } }, { \"name\" : \"name\", \"type\" : { \"nullable\" : true, \"length\" : \"\", \"scale\" : \"\", \"signed\" : true, \"type\" : \"type\" } } ] } } } }";
                    ApiUtil.setExampleResponse(request, "application/json", jsonString);
                    break;
                }
            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
        // ResponseEntity<GetAssetResponse> response1 = ResponseEntity.ok().body(g);
        //return response1;

    }

}