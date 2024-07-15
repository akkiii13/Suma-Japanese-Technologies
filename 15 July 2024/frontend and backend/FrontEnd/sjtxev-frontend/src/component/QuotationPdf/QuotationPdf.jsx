import CompanyLogo from "../../images/CompanyLogo.png";
import PhoneSvg from "../../../public/SVGs/Phone";
import EmailSvg from "../../../public/SVGs/Email";
import WebsiteSvg from "../../../public/SVGs/Website";
import { Page, Text, View, Document, StyleSheet } from "@react-pdf/renderer";
import "./QuotationPdf.css";

const styles = StyleSheet.create({
  page: {
    flexDirection: "row",
    backgroundColor: "#E4E4E4",
    padding: "2.54cm 3.17cm 0 3.17cm",
  },
  section: {
    margin: 10,
    padding: 10,
    flexGrow: 1,
  },
  companyName: {
    fontSize: 'large',
    color: "#7ab629",
    textDecoration: 'underline',
    display: 'inline-block',
    fontWeight: 'bold',
  },
});

const QuotationPdf = () => {
  return (
    <Document title="">
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Text>Section #1</Text>
        </View>
      </Page>
    </Document>
  );
};

export { QuotationPdf };
