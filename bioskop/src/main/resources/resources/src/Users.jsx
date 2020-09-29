import React from "react"

import PrikazKarata from "./PrikazKarata"
import PrikazKorisnika from "./PrikazSvihKorisnika"
import FilterUsers from "./FilterUsers"
import Korisnik from "./Korisnik"

import $ from "jquery"

class Users extends React.Component{

    constructor(){
        super()
        this.state = {
            users: [],
            user: "",
            admin: false
        }
        this.handleChange=this.handleChange.bind(this)
        this.filter=this.filter.bind(this);
        this.handleClick=this.handleClick.bind(this);
        this.deleteUser=this.deleteUser.bind(this);
        this.cancel=this.cancel.bind(this)
    }

    componentDidMount(){
    
        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        fetch('http://localhost:8080/bioskop/users',config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    users: res
                })
            })
    }

    cancel(){
        this.setState({user:""})
        $('#idKorisnik').hide()
        $('#prikazKarata').hide()
        $('#tableUsers').show()
    }

    handleChange(event){
        const checked = event.target.checked
        if(checked === true){
            this.setState(prevState => {
                const updateUsers=prevState.users.map(u => {
                    if(u.korisnickoIme === prevState.user.korisnickoIme){
                        u.authorities="ROLE_USER ROLE_ADMIN"
                        var config={
                            dataType: 'json',
                            method: 'put',
                            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                            body: JSON.stringify(u)
                          };
                
                        // On submit of the form, send a POST request with the data to the server.
                        fetch('http://localhost:8080/bioskop/users',config)
                            .then(res => res.json())
                            .then(res => console.log(res))
                    }
                    return u;
                })
                return {
                    users: updateUsers,
                    admin: true
                }
            })
        }else{
            this.setState(prevState => {
                const updateUsers=prevState.users.map(u => {
                    if(u.korisnickoIme === prevState.user.korisnickoIme){
                        u.authorities="ROLE_USER "
                        var config={
                            dataType: 'json',
                            method: 'put',
                            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                            body: JSON.stringify(u)
                          };
                
                        // On submit of the form, send a POST request with the data to the server.
                        fetch('http://localhost:8080/bioskop/users',config)
                            .then(res => res.json())
                            .then(res => console.log(res))
                    }
                    return u;
                })
                return {
                    users: updateUsers,
                    admin: false
                }
            })
        }
    }

    filter(){
        var config={
            method: 'get',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')}
          };

        const username=$('#idUsername').val() === "" ? "null":$('#idUsername').val();
        const role=$('#idRole').val() === "" ? "null":$('#idRole').val();
        const sort=$('#idSort').val() === "" ? "null":$('#idSort').val();

        fetch('http://localhost:8080/bioskop/users/'+username+"/"+role+"/"+sort,config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    users: res
                })
            })
    }

    handleClick(event){
        const name=event.target.name
        this.state.users.map(u => {
            if(u.korisnickoIme === name){
                const a=u.authorities.includes("ROLE_ADMIN") ? true:false
                this.setState({
                    user:u,
                    admin:a
                })
            }
        })
        $('#tableUsers').hide();
    }

    deleteUser(event){
        const name = event.target.name
        var config={
            method: 'delete',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')}
          };

        // On submit of the form, send a POST request with the data to the server.
        fetch('http://localhost:8080/bioskop/users/'+name,config)

        this.setState({
            user: ""
        })
        
        this.setState(prevState => {
            const updateUsers=prevState.users.filter(u => u.korisnickoIme !== name)
            return {
                users: updateUsers
            }
        })
        
        $('#tableUsers').show()
        $('#Bioskop').children('#idKorisnik').remove()
    }

    render(){
        const prikaz = this.state.user === "" && this.state.users.map(u => <PrikazKorisnika handleClick={this.handleClick} handleChange={this.handleChange} key={u.korisnickoIme} user={u} />)
        const prikazKorisnika=this.state.user !== "" && <Korisnik cancel={this.cancel} handleChange={this.handleChange} deleteUser={this.deleteUser} state={this.state}/>
        const prikazKarata=this.state.user !== "" && <PrikazKarata users={this.props.users} cancel={this.cancel} state={this.state}/>
        
        return(
            <div id="Users">
                <div id="tableUsers">
                    <label>
                        Sortiraj po:
                            <select id="idSort" onChange={this.filter}>
                                <option value=""></option>
                                <option value="ki-asc">Korisnickom imenu, rastuce</option>
                                <option value="ki-desc">Korisnickom imenu, opadajuce</option>
                                <option value="u-asc">Ulozi, rastuce</option>
                                <option value="u-desc">Ulozi, opadajuce</option>
                            </select>
                    </label>
                    <table border="1">
                        <FilterUsers filter={this.filter} />
                        <tbody>
                            {prikaz}
                        </tbody>
                    </table>
                </div>
                {prikazKorisnika}
                {prikazKarata}
            </div>
        )
    }
}

export default Users