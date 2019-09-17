import React, { Component } from 'react';
import Collapse from 'react-bootstrap/Collapse';
import Button from 'react-bootstrap/Button';
import StoreListItems from "../components/StoreListItems";





class StoreButton extends Component{

    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };
    }



render(){
    const { open } = this.state;
        return(
            <div className="">

                <div className="text-left">

                    <>
                    <Button
                    onClick={() => this.setState({ open: !open })}
                    aria-controls="store-collapse-items"
                    aria-expanded={open}
                    >
                    {`${this.props.store.name}`}<br></br>({`${this.props.store.products.length} items`})
                    </Button>
                    <Collapse in={this.state.open}>
                    <div id="store-collapse-items">
                    <StoreListItems items={this.props.store.products}/>
                    </div>
                    </Collapse>
                    </>

                </div>
            </div>
        );
    }
}

export default StoreButton;