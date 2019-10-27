package com.elogstation.api.elogstationapi.batch;



import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.net.URL;

@Controller
public class FMCSAWebServiceClient {

    String https_url = "https://eldws.fmcsa.dot.gov/ELDSubmissionService.svc";


    public void sendData(){

        System.out.println("sent");
        //TODO: get data to be sent from database
//        trackingRepo.

        //TODO: heavy lifting
//        doPing();

        //update database to uploaded flag as true

    }


    public void doPing() {

        URL url;

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
                    .newInstance();
            SOAPConnection soapConnection = soapConnectionFactory
                    .createConnection();

            // Send SOAP Message to SOAP Server

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            //Use below (2 lines) if Host name verification needs to turned off
            MyHostnameVerifier HostVerifier = new MyHostnameVerifier();
            con.setHostnameVerifier(HostVerifier);
            con.connect();

            SOAPMessage response = soapConnection.call(createRequest(),url);


            // Print the  SOAP Response
            printResponse (response );

            soapConnection.close();
        } catch (Exception e) {
            System.err
                    .println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createRequest() throws Exception {
        // SOAP 1.1 Services
//        MessageFactory messageFactory = MessageFactory.newInstance();
        // For SOAP 1.2 services
         MessageFactory messageFactory =
         MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);

        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // String serverURI = "myhost";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

        envelope.addNamespaceDeclaration("fmc",
                "http://www.fmcsa.dot.gov/schemas/FMCSA.ELD.Infrastructure");

        envelope.addNamespaceDeclaration("add",
                "http://www.w3.org/2005/08/addressing");

        SOAPBody soapBody = envelope.getBody();
        SOAPElement pingElement = soapBody.addChildElement(
                "Ping", "fmc");

        SOAPElement dataElement = pingElement
                .addChildElement("data", "fmc");

        SOAPElement eLDIdentifierElement = dataElement
                .addChildElement("ELDIdentifier", "fmc");

        eLDIdentifierElement.addTextNode("TESTXX");

        SOAPElement eLDRegistrationIdElement = dataElement
                .addChildElement("ELDRegistrationId", "fmc");

        eLDRegistrationIdElement.addTextNode("TEST");

//        MimeHeaders headers = soapMessage.getMimeHeaders();

        SOAPHeader header = envelope.getHeader();

        SOAPElement toHeaderElement = header
                .addChildElement("To", "add");

        toHeaderElement.addTextNode("https://eldws.fmcsa.dot.gov/ELDSubmissionService.svc");


        SOAPElement actionHeaderElement = header
                .addChildElement("Action", "add");

        actionHeaderElement.addTextNode("http://www.fmcsa.dot.gov/schemas/FMCSA.ELD.Infrastructure/IELDSubmissionService/Ping");

//        se.setMustUnderstand(true);
//        se.addTextNode("some text");
//        se.addAttribute(envelope.createName("s:mustUnderstand"),"1"); //S: or s: depending on xmlns


        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printResponse(SOAPMessage soapResponse)
            throws Exception {
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

    //  This code is used when the server certificate 's CN is different than host name or IP.
    class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            if (hostname.equals("hostame or IP of the from WSDL"))
                return true;
            else
                return false;
        }
    }
}
