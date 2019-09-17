import React, { Component } from 'react';
import StoreButtonList from "./StoreButtonList";
import UserList from "../components/UserList";

import styled, { keyframes } from 'styled-components';
import {bounce} from 'react-animations';
import {withAuthUser} from './Session';
import {compose} from 'recompose';

const bounceAnimation = keyframes`${bounce}`;
const BouncyDiv = styled.div`
  animation: 1s ${bounceAnimation} ;
`;




class List extends Component{

    constructor(props) {
        super(props);

        this.state = {
            list: {}
        };
    }
    componentDidMount(){
        if(this.props.authUser){
            fetch(`https://api-41200.herokuapp.com/lists/${this.props.authUser.uid}`)
            .then(response => response.json())
            .then(data => data.length?this.setState({list: data[0]}):"");
        }
    }
render(){

        return(
            <div className="list-wrapper">
                <BouncyDiv className="mb-4">
                    <h1 className="header-wrapper">My List (Navigate off this page if things don't load)</h1>
                </BouncyDiv>

                <div className="text-left">
                    <StoreButtonList list={this.state.list}/>
                </div>
                <hr/>
                <div className=" text-left col-12 mt-4">
                    <UserList list={this.state.list}/>
                </div>
            </div>
        );
    }
}

export default compose(withAuthUser)(List);