import axios from 'axios';


const MONUMENT_API_BASE_URL="http://localhost:8080/Monument";

// /addinscription




///MONUMENT
const ADD_MONUMENT_API_BASE_URL="http://localhost:8080/addMonument";
const GETBYID_MONUMENT_API_BASE_URL="http://localhost:8080/Monument";
const UPDATE_MONUMENT_API_BASE_URL="http://localhost:8080/updatemonument";
const DELETE_MONUMENT_API_BASE_URL="http://localhost:8080/deletemonument";


class RegistrationService{
    

    getMonuments(){
        return axios.get(MONUMENT_API_BASE_URL);
    }

    createMonument(registration) {
        return axios.post(ADD_MONUMENT_API_BASE_URL,registration);
    }

    getMonumentById(registrationId){
        return axios.get(GETBYID_MONUMENT_API_BASE_URL + '/' + registrationId);
    }
    deleteMonument(registrationId){
        return axios.delete(DELETE_MONUMENT_API_BASE_URL + '/' + registrationId);
    }


    updateMonument(registration,registration_id){
        return axios.put(UPDATE_MONUMENT_API_BASE_URL + '/' + registration_id,registration);
    }
}

export default new RegistrationService()

