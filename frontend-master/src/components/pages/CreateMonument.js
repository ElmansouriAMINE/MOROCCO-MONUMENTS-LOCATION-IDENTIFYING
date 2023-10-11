import React, { Component } from 'react'
import RegistrationService from '../services/RegistrationService';



export default class CreateRegistration extends Component {
    constructor(props){
        super(props)
        

        this.state={
          nom:'',
          lattitude:'',
          longitude:'',
         
          hidediv: false


        }
        this.changeLastNameHandler=this.changeLastNameHandler.bind(this);
        
        this.changeMatriculeHandler=this.changeMatriculeHandler.bind(this);
        this.changeNumberHandler=this.changeNumberHandler.bind(this);
       
       
       
        

        this.saveRegistration = this.saveRegistration.bind(this); 

    
    }
    handleClick=(event) =>{
      this.setState({
         hidediv: true
       });
     }
    // saveRegistration method
    saveRegistration = (r) => {
       r.preventDefault();
       let registration = {
          nom:this.state.nom,
          
          lattitude:this.state.lattitude,
          longitude:this.state.longitude,
          
         
       };
       console.log('registration => ' +JSON.stringify(registration));

       RegistrationService.createMonument(registration).then(res =>{
            this.props.history.push('/monuments');
       });

    }

    

    changeLastNameHandler=(event) => {
      this.setState({nom:event.target.value});
    }
    
    changeMatriculeHandler=(event) => {
      this.setState({ lattitude:event.target.value});
    }
    changeNumberHandler=(event) => {
      this.setState({longitude:event.target.value});
    }
    
    // changeUniversityHandler=(event) => {
    //   this.setState({photo:event.target.value});
    // }
   
    cancel(){
      this.props.history.push('/monuments');
    }
   
    
  render() {
    return (

      <div>
        

<div class="container-fluid">


<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Monuments</h1>
    <a href="{{route('users.index')}}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
            class="fas fa-arrow-left fa-sm text-white-50"></i> Back</a>
</div>


<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Monuments</h6>
    </div>

                <form>
                <div class="card-body">
                  {/* nom first_name lattitude longitude payment_date registration_date photo year amount */}
                  <div class="form-group row">

               
                <div class="col-sm-6 mb-3 mt-3 mb-sm-0">
                <label>Nom</label>
                    <input placeholder='Nom' name="nom" className='form-control'
                    value={this.state.nom} onChange={this.changeLastNameHandler} required
                    />

                </div>
                <div class="col-sm-6 mb-3 mt-3 mb-sm-0">
                <label>Lattitude</label>
                    <input placeholder='lattitude' name="lattitude" className='form-control'
                    value={this.state.lattitude} onChange={this.changeMatriculeHandler}
                    />
                   
                </div>
                <div class="col-sm-6 mb-3 mt-3 mb-sm-0">
                <label>longitude</label>
                    <input placeholder='longitude' type='longitude' name="longitude" className='form-control'
                    value={this.state.longitude} onChange={this.changeNumberHandler}
                    />
                   
                </div>
                
                {/* <div class="col-sm-6 mb-3 mt-3 mb-sm-0">
                <label>photo</label>
                    <input placeholder='photo' name="photo" className='form-control'
                    value={this.state.photo} onChange={this.changeUniversityHandler}
                    />
                </div> */}
               
                  
                  
                 
                <div class="col-sm-6 mb-3 mt-3 mb-sm-0">
                  <button className='btn btn-success' onClick={this.saveRegistration}>Save</button>
                  <button className='btn btn-danger' onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button>
                  {/* <div className="date-range" hidden = {this.state.hidediv}> */}
                  {/* <button className='btn btn-primary' type="submit"   onClick={this.handleClick} style={{marginLeft:"10px"}}>Pay Fees</button> */}
                  {/* </div>
           */}
                  </div>  
                  </div>
                  </div>
                </form>
                
              </div>
            </div>
            



            {/* Payment ajouté le 18 */}

            
                  
                



            {/* end payment */}
           
            </div>

    )
  }
}
