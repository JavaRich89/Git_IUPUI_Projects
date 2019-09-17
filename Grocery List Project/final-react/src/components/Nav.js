import React, { Component } from 'react';
import {NavLink} from 'react-router-dom';

class Nav extends Component{
    render(){
        return(
            <nav className="nav">
                <NavLink className="nav-spacing" exact activeClassName="active" to={"/"}><h6>Search</h6></NavLink>

                <NavLink className="nav-spacing" activeClassName="active" to={"/List"}><h6>List</h6></NavLink>

                <NavLink className="nav-spacing" activeClassName="active" to={"/Admin"}><h6>Admin</h6></NavLink>

                <NavLink className="nav-spacing" activeClassName="active" to={"/TestAPI"}><h6>TestAPI</h6></NavLink>
            </nav>
        );
    }
}

export default Nav;