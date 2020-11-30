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
    axios.get('https://api.npms.io/v2/search?q=react')
        .then(response => this.setState({ totalRedactPackages: response.data.total }));
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
