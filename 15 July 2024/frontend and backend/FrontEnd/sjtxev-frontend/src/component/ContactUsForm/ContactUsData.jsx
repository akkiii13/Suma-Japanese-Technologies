import React, { useEffect, useState } from "react";
import { GetAllInquiries } from "../../services/CustomerContactService";
import { Table } from "reactstrap";

function ContactUsData() {
  const [allInquiries, setAllInquiries] = useState(null);

  useEffect(() => {
    GetAllInquiries()
      .then((data) => {
        console.log(data);
        setAllInquiries(data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <Table striped>
      <thead>
        <tr>
          <th>Id</th>
          <th>Full name</th>
          <th>Mobile number</th>
          <th>Email</th>
          <th>Subject</th>
          <th>Message</th>
        </tr>
      </thead>
      <tbody>
        {allInquiries?.map((inquiry) => (
          <tr key={inquiry.contactUsId}>
            <td>{inquiry.contactUsId}</td>
            <td>{inquiry.fullName}</td>
            <td>{inquiry.mobileNumber}</td>
            <td>{inquiry.email}</td>
            <td>{inquiry.subject}</td>
            <td>{inquiry.message}</td>
          </tr>
        ))}
        {!allInquiries && (
          <tr>
            <td colSpan="6">Loading...</td>
          </tr>
        )}
      </tbody>
    </Table>
  );
}

export default ContactUsData;
