import React, { Component } from 'react';
import ProductCard from "./ProductCard";
import Search from "./Search";

import styled, { keyframes } from 'styled-components';
import {bounce} from 'react-animations';

const bounceAnimation = keyframes`${bounce}`;
const BouncyOnceDiv = styled.div`
  animation: 1s ${bounceAnimation} ;
`;

class Home extends Component{
    constructor(props){
        super(props);
        this.state = {
            products: [],
            selectedStore: ""
        }
        this.setProducts = this.setProducts.bind(this);
        this.setSelectedStore = this.setSelectedStore.bind(this);
    }

    setSelectedStore = (selectedStore) => {
        this.setState({selectedStore});
    }

    setProducts = (products) => {
        this.setState({products});
    }
    render(){
        return(
            <div>
                <div className="">
                    <BouncyOnceDiv className="mb-4">
                        <h1 className="header-wrapper">Grocery List App!</h1>
                    </BouncyOnceDiv>
                </div>
                <div className="search-wrapper">
                    <Search setProducts={this.setProducts} products={this.state.products} setSelectedStore={this.setSelectedStore} selectedStore={this.state.selectedStore}/>
                </div>
                <div className="">
                    <div className="mt-4 product-wrapper">
                        <ProductCard setProducts={this.setProducts} products={this.state.products} selectedStore={this.state.selectedStore}/>
                    </div>
                </div>
            </div>
        );
    }
}

export default Home;