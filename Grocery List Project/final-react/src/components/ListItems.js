import React, { Component } from 'react';
import Collapse from 'react-bootstrap/Collapse';
import Button from 'react-bootstrap/Button';

import styled, { keyframes } from 'styled-components';
import {bounce} from 'react-animations';

const bounceAnimation = keyframes`${bounce}`;
const BouncyDiv = styled.div`
  animation: 1s ${bounceAnimation} infinite;
`;




class List extends Component{

    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };
    }





render(){
    const { open } = this.state;
        return(
            <div className=" mt-2">

                <div className="text-left">


                    <div id="">
                        <li>item (store)</li>
                        <li>item (store)</li>
                        <li>item (store)</li>
                        <li>item (store)</li>
                    </div>


                </div>
            </div>
        );
    }
}

export default List;