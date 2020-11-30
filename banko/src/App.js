import React from 'react';

import './App.css';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';

/*
function App() {
  return (
    <div className="App">

      <Navbar />
      <Footer />
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
