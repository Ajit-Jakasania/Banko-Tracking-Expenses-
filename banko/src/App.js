import React from 'react';
import { Component } from 'react';
import axios from 'axios';

import WelcomePage from './pages/welcomePage/WelcomePage'
import LoginPage from './pages/loginPage/LoginPage'
import RegisterPage from './pages/registerPage/RegisterPage'
import GroupRegisterPage from './pages/groupRegisterPage/GroupRegisterPage'
import JoinGroupPage from './pages/joinGroupPage/JoinGroupPage'
import LoginPage2 from './pages/loginPage/LoginPage2'

import './App.css';

/*
function App() {
  return (
    <div className="App">

      <LoginPage2 />
    </div>
  );
}

export default App;
*/
/*
class App extends Component {



  render(){
    return (
      <div className="App">

        <LoginPage2 />
      </div>
    );
  }
}
*/

class App extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
        totalReactPackages: null
    };
}

componentDidMount() {
    // Simple GET request using axios
    axios.get('http://localhost:8080/userLogIn', {
      username: "user1"
    })
    .then(function(response){ 
      console.log(response)
    });
}

  render() {
      return (
          <div className="App">
              <LoginPage />
          </div>
      );
  }
}
export default App;
