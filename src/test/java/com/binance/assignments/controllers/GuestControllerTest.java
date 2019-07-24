package com.binance.assignments.controllers;

import com.binance.assignments.InvoiceApplication;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {InvoiceApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class GuestControllerTest {

    private MockMvc mockMvc;
    private InputStream is;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws FileNotFoundException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        is = new FileInputStream("test.csv");
    }


    @Test
    public void guest_shouldPass() throws Exception {
        mockMvc.perform(get("/guest"))
                .andExpect(status().isOk())
                .andExpect(view().name("guest"));
    }

    @Test
    public void upload_withValidTestFile_ShouldPass() throws Exception {
        MockMultipartFile testcsv = new MockMultipartFile("file","test.csv","multipart/form-data",is);
        mockMvc.perform(fileUpload("/upload-file").file(testcsv).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(model().attribute("data", Matchers.hasSize(3)))
                .andExpect(model().attribute("success", true))
                .andExpect(view().name("result"));
    }
    @Test
    public void upload_emptyFile_shouldFail() throws Exception {
        InputStream is_emfile =  new FileInputStream("test-empty-file.csv");
        MockMultipartFile testcsv = new MockMultipartFile("file","test.csv","multipart/form-data",is_emfile);
        mockMvc.perform(fileUpload("/upload-file").file(testcsv).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(model().attribute("success", false))
                .andExpect(model().attribute("errordata","project name is empty at row 2"))
                .andExpect(view().name("result"));
    }
    @Test
    public void upload_notUsingTemplateFile_shouldFail() throws Exception {
        InputStream is_emfile =  new FileInputStream("test-wrong-template.csv");
        MockMultipartFile testcsv = new MockMultipartFile("file","test.csv","multipart/form-data",is_emfile);
        mockMvc.perform(fileUpload("/upload-file").file(testcsv).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(model().attribute("success", false))
                .andExpect(model().attribute("errordata","Something went wrong with the uploaded file. Please review and retry"))
                .andExpect(view().name("result"));
    }
    @Test
    public void upload_noFile_shouldFail() throws Exception {
        InputStream is_emfile = null;
        MockMultipartFile testcsv = new MockMultipartFile("file","test.csv","multipart/form-data",is_emfile);
        mockMvc.perform(fileUpload("/upload-file").file(testcsv).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(model().attribute("success", false))
                .andExpect(model().attribute("errordata","File is empty"))
                .andExpect(view().name("result"));
    }

    @Test
    public void upload_invalidDate_shouldFail() throws Exception {
        InputStream invalidDate = new FileInputStream("test-invalid-date.csv");;
        MockMultipartFile testcsv = new MockMultipartFile("file","test.csv","multipart/form-data",invalidDate);
        mockMvc.perform(fileUpload("/upload-file").file(testcsv).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(model().attribute("success", false))
                .andExpect(model().attribute("errordata","Unable to convert start time to correct date time at row 2"))
                .andExpect(view().name("result"));
    }
}