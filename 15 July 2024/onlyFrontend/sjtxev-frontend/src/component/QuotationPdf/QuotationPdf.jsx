import CompanyLogo from "../../images/CompanyLogo.png";
import "./QuotationPdf.css";
import PhoneSvg from "../SVGs/Phone";
import EmailSvg from "../SVGs/Email";
import WebsiteSvg from "../SVGs/Website";
import { Page, Text, View, Document, StyleSheet } from "@react-pdf/renderer";
import ReactPDF from '@react-pdf/renderer';

const styles = StyleSheet.create({
  page: {
    flexDirection: "row",
    backgroundColor: "#E4E4E4",
  },
  section: {
    margin: 10,
    padding: 10,
    flexGrow: 1,
  },
});

// you can use a function to return the target element besides using React refs
const getTargetElement = () => document.getElementById("content-id");

ReactPDF.render(<QuotationPdf />, `${__dirname}/example.pdf`);

const QuotationPdf = () => {

  return (
    <Document title="">
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Text>Section #1</Text>
          <div>
            <button
              type="button"
              className="btn text-white pt-2 px-4 fs-6"
              onClick={() => generatePDF(getTargetElement)}
              style={{ backgroundColor: "#7AB629" }}
            >
              Generate PDF
            </button>
            <div id="content-id">
              <div className="d-flex">
                <div className="">
                  <div id="companyName">
                    <h3>SUMA JAPANESE TECHNOLOGIES PVT. LTD.</h3>
                  </div>
                  <div id="officeAddres">
                    <p>Shinde Complex, Dr. Homi Bhabha Rd,</p>
                    <p>Shindenagar, Bavdhan, Pune, Maharashtra 411021</p>
                  </div>
                  <div id="contactInfo">
                    <div className="d-flex flex-row">
                      <div id="phoneNumber" className="d-flex flex-row">
                        <div style={{ marginTop: 12 + "px" }}>
                          <PhoneSvg />
                        </div>
                        <div style={{ marginLeft: 10 + "px" }}>
                          <a
                            href="tel:8799997206"
                            target="_blank"
                            rel="noopener noreferrer"
                          >
                            <span>+91 8799997206</span>
                          </a>
                        </div>
                      </div>
                      <div id="emailAddress" className="d-flex flex-row">
                        <div style={{ marginTop: 12 + "px" }}>
                          <EmailSvg />
                        </div>
                        <div style={{ marginLeft: 10 + "px" }}>
                          <a
                            href="mailto:sales@sjtev.com"
                            target="_blank"
                            rel="noopener noreferrer"
                          >
                            <span>sales@sjtev.com</span>
                          </a>
                        </div>
                      </div>
                    </div>
                    <div id="websiteAddress" className="d-flex flex-row">
                      <div style={{ marginTop: 12 + "px" }}>
                        <WebsiteSvg />
                      </div>
                      <div style={{ marginLeft: 10 + "px" }}>
                        <a
                          href="https://www.sjtev.com"
                          target="_blank"
                          rel="noopener noreferrer"
                        >
                          <span>www.sjtev.com</span>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="justify-content-end companyLogo align-content-end">
                  <img
                    src={CompanyLogo}
                    alt="CompanyLogo"
                    className="companyLogo"
                  />
                </div>
              </div>
            </div>
          </div>
        </View>
      </Page>
    </Document>
  );
};

export default QuotationPdf;
