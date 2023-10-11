import React from 'react';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Services from './components/pages/Services';
import Products from './components/pages/Products';
import SignUp from './components/pages/SignUp';
import CreateMonument from './components/pages/CreateMonument';
import UpdateMonument from './components/pages/UpdateMonument';
import ListesMonuments from './components/pages/ListesMonuments';


function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/services' component={Services} />
          <Route path='/products' component={Products} />
          <Route path='/sign-up' component={SignUp} />
          <Route path="/addmonument" component={CreateMonument} />
          <Route path="/updateMonument/:id" component={UpdateMonument} exact/>
                            {/* <Route path="/deleteRegistration/:id" exact component={ListRegistration} /> */}
          <Route path="/monuments" component={ListesMonuments} />
        </Switch>
      </Router>
    </>
  );
}

export default App;