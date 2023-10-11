import React from 'react';

function Map(props) {
  return (
    <div className="container mt-5 mb-5">
    <iframe
      src={`https://maps.google.com/maps?q=${props.lat},${props.long}&z=15&output=embed`}
      width="1100"
      height="650"
      frameBorder="0"
      style={{ border: 0 }}
      allowFullScreen=""
      aria-hidden="false"
      tabIndex="0"
    />
    </div>
  );
}

export default Map;