import React, { useState } from 'react';
import '../assets/ConfigurationForm.css';

const ConfigurationForm = ({ onConfigSubmit }) => {
  const [totalTickets, setTotalTickets] = useState('');
  const [releaseInterval, setReleaseInterval] = useState('');
  const [retrievalInterval, setRetrievalInterval] = useState('');
  const [maxCapacity, setMaxCapacity] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const config = {
      totalTickets: parseInt(totalTickets),
      releaseInterval: parseInt(releaseInterval),
      retrievalInterval: parseInt(retrievalInterval),
      maxCapacity: parseInt(maxCapacity),
    };
    onConfigSubmit(config);
  };

  return (
    <div className="configuration-form-container">
      <h2>Enter Configuration</h2>
      <form className="configuration-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Total Tickets:</label>
          <input
            type="number"
            value={totalTickets}
            onChange={(e) => setTotalTickets(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Release Interval (ms):</label>
          <input
            type="number"
            value={releaseInterval}
            onChange={(e) => setReleaseInterval(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Retrieval Interval (ms):</label>
          <input
            type="number"
            value={retrievalInterval}
            onChange={(e) => setRetrievalInterval(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Max Capacity:</label>
          <input
            type="number"
            value={maxCapacity}
            onChange={(e) => setMaxCapacity(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;