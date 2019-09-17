import React from 'react';
import logo from './shopping-list.svg';
import Button from 'react-bootstrap/Button';
import './App.css';
import {Route} from 'react-router-dom';
import NavBar from './components/NavBar';
import {MuiThemeProvider, createMuiTheme} from '@material-ui/core/styles';
import Home from "./components/Home";
import Admin from "./components/Admin";
import List from "./components/List";
import TestAPI from "./components/TestAPI";
import {withAuthentication} from './components/Session';

import styled, { keyframes } from 'styled-components';
import {bounce, fadeOutDownBig} from 'react-animations';

const bounceAnimation = keyframes`${bounce}`;
const BouncyDiv = styled.div`
  animation: 1s ${bounceAnimation};
`;

const fadeAnimation = keyframes`${fadeOutDownBig}`;
const FadeDiv = styled.div`
  animation: 10s  ${fadeAnimation};
`;

const theme = createMuiTheme({
  palette:{
    primary:{
      light: "",
      main: "#80cbc4",
      dark:"",
      contrastText: ""
    },
    secondary:{
      light: "",
      main: "#212121",
      dark:"",
      contrastText: ""
    }
  }
});

function App() {
  return (

      <MuiThemeProvider theme={theme}>
        <div className="App">
          <div className="spacing">
            <NavBar />
          </div>





          <div className="spacing">
            <Route exact path="/" component={Home}/>
            <Route path="/Admin" component={Admin}/>
            <Route path="/List" component={List}/>
            <Route path="/TestAPI" component={TestAPI}/>
          </div>




        </div>
      </MuiThemeProvider>


  );
}


export default withAuthentication(App);
