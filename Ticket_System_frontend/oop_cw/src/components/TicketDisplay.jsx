import React from 'react';
import '../assets/TicketDisplay.css';

const TicketDisplay = ({ ticketCount }) => {
  return (
    <div className="ticket-display-container">
      <h3>Current Ticket Availability</h3>
      <p>Total Tickets: {ticketCount}</p>
    </div>
  );
};

export default TicketDisplay;
