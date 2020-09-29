import React from "react"

import NovaProjekcijaForm from "./NovaProjekcijaForm"

import $ from "jquery"

class NovaProjekcija extends React.Component{
    constructor(){
        super()
        this.state = {
            film:"",
            datumVremePrikazivanja:"",
            tipProjekcije:"",
            sala:"",
            cenaKarte:"",
            sale:[],
            saleZaIzbor:[],
            tipoviZaIzbor:["2D", "3D", "4D"],
            id:"",
            napravio:"",
            filmovi:[]
        }
        this.handleSubmit=this.handleSubmit.bind(this)
        this.handleChange=this.handleChange.bind(this)
        this.cancel=this.cancel.bind(this)
    }

    handleSubmit(e){
        e.preventDefault();
        const {film,datumVremePrikazivanja,tipProjekcije,sala,cenaKarte}=this.state
        if(film === "" || datumVremePrikazivanja === "" || tipProjekcije === "" || sala === "" || cenaKarte === ""){
            alert("Sva polja moraju biti popunjena!")
        }else{
            const projekcija={
                film:this.state.film,
                tipProjekcije:this.state.tipProjekcije,
                sala:this.state.sala,
                cenaKarte:this.state.cenaKarte,
                napravio: this.props.state.logedUsername,
                datumVremePrikazivanja:this.state.datumVremePrikazivanja
            }
            console.log(JSON.stringify(projekcija))
    
            var config={
                dataType: 'json',
                contentType:"application/json; charset=utf-8",
                method: 'post',
                headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                body: JSON.stringify(projekcija)
              };
    
            // On submit of the form, send a POST request with the data to the server.
            fetch('http://localhost:8080/bioskop/projekcije/add',config)
                .then(res => res.json())
                .then(res => {
                    console.log(res)
                    if(res.result==="success"){
                        this.state.sale.map(s => {
                            if(s.id == projekcija.sala){
                                this.setState({
                                    id:res.id,
                                    datumVremePrikazivanja:res.datum,
                                    sala:s.naziv,
                                    napravio:this.props.state.logedUsername
                                })
                            }
                        })
                        this.props.state.filmovi.map(f => {
                            if(f.id == projekcija.film){
                                this.setState({
                                    film:f.naziv
                                })
                            }
                        })
                        const novaProjekcija={
                            id:this.state.id,
                            film:this.state.film,
                            tipProjekcije:this.state.tipProjekcije,
                            sala:this.state.sala,
                            cenaKarte:this.state.cenaKarte,
                            napravio: this.props.state.logedUsername,
                            datumVremePrikazivanja:this.state.datumVremePrikazivanja
                        }

                        this.props.handleToUpdateAddProjekcije(novaProjekcija)
                        this.cancel()
                        alert("Uspesno ste dodali projekciju!")
                        
                    }else if(res.result==="error"){
                        alert("Datum i vreme prikazivanja projekcije su vec rezervisani!")
                    }
                })
        }
    }

    cancel(){
        this.props.setFalse()
        this.props.setTrue()
        $('#idProjekcije').show();
        $('#NovaProjekcija').remove();
    }

    componentDidMount(){

        const conf={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };
        fetch('http://localhost:8080/bioskop/sale',conf)
            .then(res => res.json())
            .then(response => {
                this.setState({
                    sale: response,
                    saleZaIzbor: response,
                    filmovi:this.props.state.filmovi
            })
        })
    }

    handleChange(event){
        const {name,value} = event.target
        if(name === "datumVremePrikazivanja"){
            const curentDateTime=new Date()
            const rezervacjija=new Date(value)
            if(curentDateTime>rezervacjija){
                alert("Vreme prikazivanje projekcije ne sme biti u proslosti!")
            }else{
                this.setState({
                    [name]:value
                })
            }
        }else if(name === "sala"){
            if(value == ""){
                this.setState({
                    tipoviZaIzbor: ["2D", "3D", "4D"]
                })
            }else{
                this.state.sale.map(s => {
                    if(s.id==value){
                        const tipovi=s.tip.split(" ").filter(i => i != "")
                        this.setState({
                            tipoviZaIzbor:tipovi
                        })
                    }
                })
            }
            this.setState({
                [name]:value
            })
        }else if(name === "tipProjekcije"){
            if(value === ""){
                this.setState({saleZaIzbor:this.state.sale})
            }else{
                this.setState({

                })
                var filterSale=[]
                this.state.sale.map(s => {
                if(s.tip.includes(value)){
                    filterSale.push(s)
                }
                })
                this.setState({saleZaIzbor:filterSale})
            }
            this.setState({
                [name]:value
            })
        }else{
            this.setState({
                [name]:value
            })
        }
    }

    render(){
        return(
            <div id="NovaProjekcija">
                <NovaProjekcijaForm state={this.state} handleChange={this.handleChange} filmovi={this.state.filmovi} handleSubmit={this.handleSubmit}/>
            </div>
        )
    }
}

export default NovaProjekcija