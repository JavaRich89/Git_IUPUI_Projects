import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';


class UserList extends Component{

    constructor(props) {
        super(props);

        this.state = {
            open: true,
            items: [],
            loaded: false
        };
    }

    componentDidUpdate(prevProps, prevState, snapshot){
        const list = this.props.list;
        if(list.stores){
            let products = [];
            list.stores.map(store=>{
                let tempProds = [];
                store.products.map(prod=>{
                    tempProds.push({
                        store,
                        prod
                    })
                })
                products.push(...tempProds);
            })
        if(prevState.loaded === false){
            this.setState({items: products, loaded: true});
        }
        }
    }

    handleDelete = (e) => {
        const value = JSON.parse(e.target.value);
        let newBody = this.props.list;
        newBody.stores = newBody.stores.filter(store=>{
            if(store.name === value.store.name){
                store.products = store.products.filter(product=>{
                    if(product.ProductName === value.prod.ProductName){
                        return false;
                    }else{
                        return true;
                    }
                })
            }
            if(store.products.length){
                return true;
            }else{
                return false;
            }
        })
        return fetch(`https://api-41200.herokuapp.com/lists/${newBody._id}`,{
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newBody)
        })
    }


render(){
    const { open } = this.state;
        return(
            <div className="mt-2">
                <div className="text-left">
                    <h4 className="mb-4">My Shopping List</h4>
                    <div className="col-8">
                        {this.state.items.map((item, i)=>
                            <div className="d-flex flex-row mb-2" key={i}>
                                <li className="mr-4">{item.prod.ProductName} ({item.store.name})</li>
                                <Button className="mr-auto" variant="danger" size="sm" onClick={this.handleDelete} value={JSON.stringify(item)}>Delete</Button>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        );
    }
}

export default UserList;