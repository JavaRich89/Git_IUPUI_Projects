import React, { Component } from 'react';
import StoreButton from "../components/StoreButton";




class StoreButtonList extends Component{

    constructor(props) {
        super(props);

        this.state = {
            open: true,
        };
    }



render(){
    const { open } = this.state;
        return(
            <div className="">
                <div className="d-flex flex-row justify-content-center text-left">
                    {this.props.list.stores
                        ? this.props.list.stores.map((store, i)=>
                            <div className="mr-4" key={i}>
                                <StoreButton className="" store={store}/>
                            </div>
                        )
                        : ""
                    }
                </div>
            </div>
        );
    }
}

export default StoreButtonList;