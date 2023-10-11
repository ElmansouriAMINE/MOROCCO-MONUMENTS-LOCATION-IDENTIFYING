import React, {useRef ,Component } from 'react'
import RegistrationService from '../services/RegistrationService'
import '../Cards.css';
import CardItem from '../CardItem';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
export default class ListesMonuments extends Component {
    constructor(props){
        super(props)
        // const componentRef = useRef();
        // const handlePrint = useReactToPrint({
        //   content: () => componentRef.current,
        //   documentTitle:'Monument Fees',
        //   onAfterPrint: () =>alert('Print success')
        // });

              

        this.state={
            registrations:[],
            show:false,
            id:null,
            nom:'',
            lattitude:'',
            longitude:'',
            photo:'',
            value:'',
            tableFilter:[],
            dataSource:[],
            universities:[]
            
            
        }

        this.addMonument=this.addMonument.bind(this);
        // this.filterData=this.filterData.bind(this);
        this.editMonument=this.editMonument.bind(this);
        this.deleteMonument=this.deleteMonument.bind(this);


        this.changeNomHandler=this.changeNomHandler.bind(this);
        this.changeLattitudeHandler=this.changeLattitudeHandler.bind(this);
        this.changeLongitudeHandler=this.changeLongitudeHandler.bind(this);
        this.changePhotoHandler=this.changePhotoHandler.bind(this);
     



        // this.updateRegistration = this.updateRegistration.bind(this);
    }
    
    
    componentDidMount(){
     
        
     
        RegistrationService.getMonuments().then((res) => {
             this.setState({registrations: res.data});
             this.setState({dataSource: res.data});
        });
        RegistrationService.getMonumentById(this.state.id).then( (res) => {
          let registration = res.data;
          this.setState({

           
            nom:registration.nom,
            lattitude:registration.lattitude,
            longitude:registration.longitude,
            photo:registration.photo,

          });
      });
    }

    deleteMonument(id){
      RegistrationService.deleteMonument(id).then( res => {
           this.setState({registrations: this.state.registrations.filter(regist => regist.id !==id+1)});
      }); 

    }

    addMonument= (e) => {
      e.preventDefault();
      window.history.pushState({}, undefined, "/addmonument");
      window.history.go();
      
      
    }

    editMonument(id){

      // this.props.history.push(`/updateRegistration/${id}`);
      RegistrationService.getMonumentById(id).then( (res) => {
        let registration = res.data;
        this.setState({
            id:registration.id,
            nom:registration.nom,
            lattitude:registration.lattitude,
            longitude:registration.longitude,
            photo:registration.photo,
           

        });
    });
    }

    // filterData=(e)=>{
    //   if(e.target.value !=""){
    //     this.setState({value:e.target.value});
    //     const filterTable=this.state.dataSource.filter(o=>Object.keys(o).some(k=>
    //       String(o[k]).toLowerCase().includes(e.target.value.toLowerCase())));
    //       this.setState({tableFilter:[...filterTable]})

    //   }else{
    //     this.setState({value:e.target.value});
    //     this.setState({dataSource:[...this.state.dataSource]});
        
    //   }


    // }



  //   updateRegistration = (r) => {
  //     r.preventDefault();
  //     let registration = {
  //        last_name:this.state.last_name,
  //        first_name:this.state.first_name,
  //        matricule:this.state.matricule,
  //        number:this.state.number,
  //        payment_date:this.state.payment_date,
  //        registration_date:this.state.registration_date,
  //        university:this.state.university,
  //        year:this.state.year,
  //        amount:this.state.amount
  //     };
  //     console.log('registration => ' +JSON.stringify(registration));

  //     RegistrationService.updateRegistration(registration,this.state.id).then( res => {

  //                this.props.history.push('/registrations');
  //     });

  //  }

   

   

   changeNomHandler=(event) => {
     this.setState({last_name:event.target.value});
   }
   changeLattitudeHandler=(event) => {
     this.setState({first_name:event.target.value});
   }
   changePhotoHandler=(event) => {
     this.setState({ matricule:event.target.value});
   }
  
   changeLongitudeHandler=(event) => {
     this.setState({amount:event.target.value});
   }




    // ViewDataTableRegistration(){
    //   this.props.push('/AllShoolFees');
    // }
    

  
render() {
        
    return (
      <div>
         <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800"></h1>
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="./">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Monument</li>
            </ol>

        
          </div>
          <div class="row"></div>
            <div class="col-lg-12">
              <div class="card mb-4 p-2">
                  <center><form className="navbar-search" style={{width:"70%"}}>
                      <div className="input-group" >
                        <input type="text" className="form-control bg-light border-1 small" placeholder="What do you want to look for?"
                        value={this.state.value} onChange={(e) => this.setState({value:e.target.value})
                      }
                    
                          aria-label="Search" aria-describedby="basic-addon2"  sx={{borderColor: "#3f51b5;",width:"100%"}} id="registrationId"/>
                        <div className="input-group-append">
                          <button className="btn btn-primary" type="button" onClick={(e)=>{
                          const result = this.state.registrations.filter(registration => {
                            return registration.nom.toLowerCase().match(this.state.value.toLowerCase());
                            
                            
                           });
                    
                           this.setState({tableFilter:result});
                    
                    
                        }}>
                            <i className="fas fa-search fa-sm"></i>
                          </button>
                        </div>
                      </div>
                    </form></center>
        {/* <h2 className="text-center mb-0">Monument fees Table</h2> */}
        
        {/* <a href={"/AllShoolFees"}>DataTable of Registrations</a> */}
        {/* <button className='btn btn-primary' onClick={this.ViewDataTableRegistration}>DataTable of Registrations</button> */}
        
         <div className='row' >  {/*ref={componentRef} */}
         <div style={{ display: this.state.show ? "block" : "none" }}>
          
        </div>
        <div className="row ml-3">
          <button className='btn btn-primary' onClick={this.addMonument}>Add Monument</button>
          {/* <button className='btn btn-primary' onClick={handlePrint}>Add Registration</button> */}
        </div> 
        <div class="table-responsive p-3 ">
                  <table class="table align-items-center table-flush table-hover table-bordered">
                     <thead class="thead-light">
              <tr>
                <th>Nom</th>
                <th>lattitude</th>
                <th>longitude</th>
                <th>Actions</th>
              </tr>
              </thead>

              <tbody>
                {
                  this.state.value.length >0 ?  this.state.tableFilter.map(
                        registration =>
                        <tr key ={registration.id}>
                            <td>{registration.nom}</td>
                            <td>{registration.lattitude}</td>
                            <td>{registration.longitude}</td>
                            <td>
                            <a  data-toggle="modal" data-target="#updateModal">

                              <button className="btn btn-info" onClick ={ () => this.editMonument(registration.id)}>Update</button>
                              
                                        </a>
                              {/* <button onClick ={ () => this.editMonument(registration.id)} className="btn btn-info">Update</button> */}
                              
                              <a  data-toggle="modal" data-target="#deleteModal">
                              <button style={{marginLeft:"12px"}} className="btn btn-danger">Delete</button>
                                        </a>

                                      <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalExample"
                                          aria-hidden="true">
                                          <div class="modal-dialog" role="document">
                                              <div class="modal-content">
                                                  <div class="modal-header">
                                                      <h5 class="modal-title" id="deleteModalExample">Are you Sure You wanted to Delete?</h5>
                                                      <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                          <span aria-hidden="true">×</span>
                                                      </button>
                                                  </div>
                                                  <div class="modal-body">Select "Delete" below if you want to delete The client fes informations!.</div>
                                                  <div class="modal-footer">
                                                      <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                                      <button data-dismiss="modal" style={{marginLeft:"12px"}} onClick ={ () => this.deleteMonument(registration.id)} className="btn btn-danger">Delete</button>
                                                  </div>
                                              </div>
                                          </div>
                                  </div>
                                  {/* debut update model */}

                                  <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalExample"
                                          aria-hidden="true">
                                          <div class="modal-dialog" role="document">
                                              <div class="modal-content">
                                                  <div class="modal-header">
                                                      <h5 class="modal-title" id="updateModalExample">Update</h5>
                                                      <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                          <span aria-hidden="true">×</span>
                                                      </button>
                                                  </div>
                                                  <div class="modal-body">
                                                    {/* begin update */}

                                                    
                                                      
                                                        <div className ="card col">
                                                          {/* <h3 className='text-center'>Edit Registration</h3> */}
                                                          <div className='card-body'>
                                                            <form>
                                                              {/* last_name first_name matricule number payment_date registration_date university year amount */}
                                                              <div className ="row d-sm-flex align-items-center justify-content-between mb-4" >

                                                              <div className='form-group'>
                                                                <label>Nom</label>
                                                                <input placeholder='nom' name="nom" className='form-control'
                                                                value={this.state.nom} onChange={this.changeNomHandler} required
                                                                />
                                                              </div>
                                                              <div className='form-group'>
                                                                <label>latittude</label>
                                                                <input placeholder='Lattitude' name="latitude" className='form-control'
                                                                value={this.state.lattitude} onChange={this.changeLattitudeHandler}
                                                                />
                                                              </div>

                                                
                                                              
                                                              <div className='form-group'>
                                                                <label>longitude</label>
                                                                <input placeholder='longitude'  name="longitude" className='form-control'
                                                                value={this.state.longitude} onChange={this.changeLongitudeHandler}
                                                                />
                                                              </div>
                                                              
                                         
                                                              
                                                              
                                                            
                                                              </div>
                                                              <center><button className='btn btn-outline-primary btn-lg ' onClick={(r) => {
                                                                                r.preventDefault();

                                                                                
                                                                             
                                                                                let registration2 = {
                                                                          
                                                                                  nom:this.state.nom,
                                                                                  lattitude:this.state.lattitude,
                                                                                  longitude:this.state.longitude,
                                                                                 
                                                                                };
                                                                                console.log('monument => ' +JSON.stringify(registration2));

                                                                                RegistrationService.updateMonument(registration2,this.state.id).then( res => {

                                                                                                    r.preventDefault();
                                                                                                    window.history.pushState({}, undefined, "/monuments");
                                                                                                    window.history.go();
                                                                                                  });

                                                                            }}
                                                              
                                                              >Save</button></center>
                                                              {/* <button className='btn btn-danger' onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button> */}

                                                            </form>
                                                            
                                                          </div>
                                                        </div>
                                                      




   
      </div>

                                                    


                                                    {/* end update */}


                                                  </div>
                                                  {/* <div class="modal-footer">
                                                      <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                                      <button data-dismiss="modal" style={{marginLeft:"12px"}} onClick ={ () => this.deleteMonument(registration.id)} className="btn btn-danger">Delete</button>
                                                  </div> */}
                                              </div>
                                          </div>
                                  
                                   {/* fin update model */}

                              
                            </td>
                        </tr>

                   
                
               

               
                                                        
                                                       

                                              


                        
                      
                 

                  )

                  :

                  this.state.registrations.map(
                    registration =>
                    <tr key ={registration.id}>
                        <td>{registration.nom}</td>
                        <td>{registration.lattitude}</td>
                        <td>{registration.longitude}</td>
                    
                        <td>
                        <a  data-toggle="modal" data-target="#updateModal">

                          <button className="btn btn-info" onClick ={ () => this.editMonument(registration.id)}>Update</button>
                          
                                    </a>
                          {/* <button onClick ={ () => this.editMonument(registration.id)} className="btn btn-info">Update</button> */}
                          
                          <a  data-toggle="modal" data-target="#deleteModal">
                          <button style={{marginLeft:"12px"}} className="btn btn-danger">Delete</button>
                                    </a>

                                  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalExample"
                                      aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                          <div class="modal-content">
                                              <div class="modal-header">
                                                  <h5 class="modal-title" id="deleteModalExample">Are you Sure You wanted to Delete?</h5>
                                                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                      <span aria-hidden="true">×</span>
                                                  </button>
                                              </div>
                                              <div class="modal-body">Select "Delete" below if you want to delete The client fes informations!.</div>
                                              <div class="modal-footer">
                                                  <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                                  <button data-dismiss="modal" style={{marginLeft:"12px"}} onClick ={ () => this.deleteMonument(registration.id)} className="btn btn-danger">Delete</button>
                                              </div>
                                          </div>
                                      </div>
                              </div>
                              {/* debut update model */}

                              <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalExample"
                                      aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                          <div class="modal-content">
                                              <div class="modal-header">
                                                  <h5 class="modal-title" id="updateModalExample">Update</h5>
                                                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                      <span aria-hidden="true">×</span>
                                                  </button>
                                              </div>
                                              <div class="modal-body">
                                                {/* begin update */}

                                                
                                                  
                                                    <div className ="card col">
                                                      {/* <h3 className='text-center'>Edit Registration</h3> */}
                                                      <div className='card-body'>
                                                        <form>
                                                          {/* last_name first_name matricule number payment_date registration_date university year amount */}
                                                          <div className ="row d-sm-flex align-items-center justify-content-between mb-4" >

                                                          <div className='form-group'>
                                                                <label>Nom</label>
                                                                <input placeholder='nom' name="nom" className='form-control'
                                                                value={this.state.nom} onChange={this.changeNomHandler} required
                                                                />
                                                              </div>
                                                              <div className='form-group'>
                                                                <label>latittude</label>
                                                                <input placeholder='Lattitude' name="latitude" className='form-control'
                                                                value={this.state.lattitude} onChange={this.changeLattitudeHandler}
                                                                />
                                                              </div>

                                                
                                                              
                                                              <div className='form-group'>
                                                                <label>longitude</label>
                                                                <input placeholder='longitude'  name="longitude" className='form-control'
                                                                value={this.state.longitude} onChange={this.changeLongitudeHandler}
                                                                />
                                                              </div>
                                                          
                                                          
                                                        
                                                          </div>
                                                          <center><button className='btn btn-outline-primary btn-lg ' onClick={(r) => {
                                                                            r.preventDefault();

                                                                            
                                                                         
                                                                            let registration2 = {
                                                                                nom:this.state.nom,
                                                                                lattitude:this.state.lattitude,
                                                                                longitude:this.state.longitude,
                                                            
                                                                            };
                                                                            console.log('monument => ' +JSON.stringify(registration2));

                                                                            RegistrationService.updateMonument(registration2,this.state.id).then( res => {

                                                                                                r.preventDefault();
                                                                                                window.history.pushState({}, undefined, "/monuments");
                                                                                                window.history.go();
                                                                                              });

                                                                        }}
                                                          
                                                          >Save</button></center>
                                                          {/* <button className='btn btn-danger' onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button> */}

                                                        </form>
                                                        
                                                      </div>
                                                    </div>
                                                  





  </div>

                                                


                                                {/* end update */}


                                              </div>
                                              {/* <div class="modal-footer">
                                                  <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                                  <button data-dismiss="modal" style={{marginLeft:"12px"}} onClick ={ () => this.deleteMonument(registration.id)} className="btn btn-danger">Delete</button>
                                              </div> */}
                                          </div>
                                      </div>
                              
                               {/* fin update model */}

                          
                        </td>
                    </tr>

               
            
           

           
                                                    
                                                   

                                          


                    
                  
             

              )
                }
              </tbody>
            </table>

{/* cards */}

{/* <iframe
              src="https://maps.google.com/maps?q=33.608419452411184,-7.632657274749517&hl=en&z=14&amp;output=embed"
              width="600"
              height="450"
              frameBorder="0"
              style={{ border: 0 }}
              allowFullScreen=""
              aria-hidden="false"
              tabIndex="0"
            /> */}

<div className='cards'>
      <h1>Check out these EPIC Monuments!</h1>
      <div className='cards__container'>
        <div className='cards__wrapper'>
          <ul className='cards__items'>
          {this.state.value.length >0 ?  this.state.tableFilter.map(
                        registration =>
                        
          

            <Link
                to={{
                  pathname: "/services",
                  state: { 
                    latittude: registration.lattitude,
                    longitude:registration.longitude,
                    photo:registration.photo,
                    nom:registration.nom
                  },
                }}
              >
          <CardItem
              src={registration.photo}
              text='Explore the hidden waterfall deep inside the Amazon Jungle'
              label={registration.nom}
         
            />
        
      </Link>

            )

            :

            this.state.registrations.map(
                registration =>
                <Link
                to={{
                  pathname: "/services",
                  state: { 
                    latittude: registration.lattitude,
                    longitude:registration.longitude,
                    photo:registration.photo,
                    nom:registration.nom
                  },
                }}
              >           
    <CardItem
      src={registration.photo}
      text='Explore the hidden waterfall deep inside the Amazon Jungle'
      label={registration.nom}
      path='/services'
    />
    </Link>

    )


        
        
        }
            
          </ul>
        </div>
      </div>
    </div>


{/* cards */}


{/* <div className="container py-1">
  <div className="d-flex justify-content-center">
    <div className="col-16">
      <div className="row row-cols-1 row-cols-md-3 g-4"> */}


      
        {/* <div className="col mt-5">
          <div className="card border-warning card-wr-bg"> <img src="https://cdn.getyourguide.com/img/location/5cf8c89592756.jpeg/68.jpg" className="card-img-top" alt="..." />
            <div className="card-body d-grid gap-3">
              <h5 className="card-title text-center text-capitalize">ahmed</h5> */}
              {/* <p className="card-text text-center p-0 pb-3">This card has supporting text below as a natural lead-in to additional content.</p> */}

            {/* </div>
            <div className="card-footer"> 
              <div><Link to={`/Localisation/`}><FontAwesomeIcon icon={faSearch} /></Link></div>
              <div><i className="bi bi-twitter" /></div>
              <div><i className="bi bi-youtube" /></div>
              <div><i className="bi bi-linkedin" /></div>
            </div>
          </div> */}
        {/* </div>       
      </div> */}
  {/* </div>
</div> */}
{/* </div> */}

        </div>
        </div>
              </div></div>
              </div>







         
          


   
      
    )
  }
}
