import React from 'react';
import '../../App.css';
import { useLocation } from "react-router-dom"
import Map from './Map';
import '../test.css';
export default function Services() {
  const location = useLocation()
  const latittude = location.state?.latittude
  const longitude = location.state?.longitude
  const photo=location.state?.photo
  const nom=location.state?.nom
 

  return(
    <>

<div className="container mt-5 mb-5">	<div className="card">	<div className="row g-0">	<div className="col-md-6 border-end">	<div className="d-flex flex-column justify-content-center">	<div className="main_image">	<img src={photo} id="main_product_image" width={450} height={270} />	</div>	<div className="thumbnail_images">	<ul id="thumbnail">	<li><img  src={photo} width={70} /></li>	<li><img  src={photo} width={70} /></li>	<li><img  src={photo} width={70} /></li>	<li><img  src={photo} width={70} /></li>	</ul>	</div>	</div>	</div>	<div className="col-md-6">	<div className="p-3 right-side">	<div className="d-flex justify-content-between align-items-center">	<h3></h3>	<span className="heart"><i className="bx bx-heart" /></span>	</div>	<div className="mt-2 pr-3 content">	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>	</div>	<h3>{nom}</h3>	<div className="ratings d-flex flex-row align-items-center">	<div className="d-flex flex-row">	<i className="bx bxs-star" />	<i className="bx bxs-star" />	<i className="bx bxs-star" />	<i className="bx bxs-star" />	<i className="bx bx-star" />	</div>	<span>441 reviews</span>	</div>	<div className="mt-5">	<span className="fw-bold">Color</span>	<div className="colors">	<ul id="marker">	<li id="marker-1" />	<li id="marker-2" />	<li id="marker-3" />	<li id="marker-4" />	<li id="marker-5" />	</ul>	</div>	</div>	<div className="buttons d-flex flex-row mt-5 gap-3">		</div>	<div className="search-option">	<i className="bx bx-search-alt-2 first-search" />	<div className="inputs">	<input type="text" name />	</div>	<i className="bx bx-share-alt share" />	</div>	</div>	</div>	</div>	</div> </div>

     {/* <h1 className='services'>{longitude}{latittude}</h1> */}
     <Map lat={latittude} long={longitude}/>

     {/* <iframe
              src={`https://maps.google.com/maps?q=${latittude},${longitude}&hl=en&z=14&amp;output=embed`}
              width="600"
              height="450"
              frameBorder="0"
              style={{ border: 0 }}
              allowFullScreen=""
              aria-hidden="false"
              tabIndex="0"
            /> */}


  </>
  );
}