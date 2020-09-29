import React, {Component} from "react"

import PrikazProjekcija from "./PrikazProjekcija"
import FilterProjekcija from "./FilterProjekcija"
import Projekcija from "./Projekcija"
import Film from "./Film"
import KupovinaKarte from "./KupovinaKarte"

import $ from "jquery"

class Projekcije extends Component{

    constructor(){
        super()
        this.state = {
            projekcije:[],
            saleZaIzbor:[],
            tipoviZaIzbor:["2D", "3D", "4D"],
            tipProjekcije:"",
            sala:"",
            projekcija: "",
            film:"",
            roles:"ROLE_USER",
            kupiKartu:false,
            brMesta:"",
        }
        this.filter=this.filter.bind(this)
        this.deleteProjekcija=this.deleteProjekcija.bind(this)
        this.handleClick=this.handleClick.bind(this)
        this.handleClickFilm=this.handleClickFilm.bind(this)
        this.cancel=this.cancel.bind(this)
        this.kupiKartu=this.kupiKartu.bind(this)
        this.setValue=this.setValue.bind(this)
    }

    componentDidMount(){
        const conf={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        fetch('http://localhost:8080/bioskop/projekcije/null/null/null/null/null/null/null/null',conf)
            .then(res => res.json())
            .then(response => {
                this.props.handleToUpdateProjekcije(response)
                this.setState({
                    projekcije: response
            })
        })

        fetch('http://localhost:8080/bioskop/sale',conf)
            .then(res => res.json())
            .then(response => {
                this.setState({
                    saleZaIzbor: response
            })
        })
    }

    filter(){

        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        const idFilm=$('#idFilm').val() === "" ? "null":$('#idFilm').val();
        const idDatumOd=$('#idDatumOd').val() === "" ? "null":$('#idDatumOd').val();
        const idDatumDo=$('#idDatumDo').val() === "" ? "null":$('#idDatumDo').val();
        const idTip=$('#idTip').val() === "" ? "null":$('#idTip').val();
        const idSala=$('#idSala').val() === "" ? "null":$('#idSala').val();
        const idCenaOd=$('#idCenaOd').val() === "" ? "null":$('#idCenaOd').val();
        const idCenaDo=$('#idCenaDo').val() === "" ? "null":$('#idCenaDo').val();
        const sort=$('#idSortProjekcije').val() === "" ? "null":$('#idSortProjekcije').val();
        fetch('http://localhost:8080/bioskop/projekcije/'+idFilm+"/"+idDatumOd+"/"+idDatumDo+"/"+idTip+"/"+idSala+"/"+idCenaOd+"/"+idCenaDo+"/"+sort,config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    projekcije: res
                })
            })
    }

    handleClick(event){
        const conf={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };
        const id=event.target.name
        this.state.projekcije.map(p => {
            if(p.id == id){
                this.setState({
                    projekcija:p
                })
                fetch('http://localhost:8080/bioskop/sedista/'+p.id,conf)
                    .then(res => res.json())
                    .then(response => this.setState({brMesta:response}))
            }
        })
        $('#tableProjekcije').hide();
    }

    handleClickFilm(event){
        const nazivFilma=event.target.name
        this.props.state.filmovi.map(f => {
            if(f.naziv == nazivFilma){
                this.setState({film:f})
            }
        })
        $('#tableProjekcije').hide();
    }

    deleteProjekcija(event){
        const id = event.target.name
        var config={
            method: 'delete',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')}
          };

        fetch('http://localhost:8080/bioskop/projekcije/delete/'+id,config)

        this.setState({
            projekcija: ""
        })
        
        this.setState(prevState => {
            const updateProjekcije=prevState.projekcije.filter(p => p.id != id)
            this.props.handleToUpdateProjekcije(updateProjekcije)
            return {
                projekcije: updateProjekcije
            }
        })
        
        this.cancel()
    }

    cancel(event){
        const name=event.target.name
        if(name === "projekcija"){
            $('#Bioskop').children('#idProjekcija').remove()
            this.setState({
                projekcija:""
            })
        }
        if(name === "film"){
            $('#Bioskop').children('#idFilm').remove()
            this.setState({
                film:""
            })
        }
        if(name === ""){
            $('#Bioskop').children('#kupovinaKarte').remove()
            this.setState({
                kupiKartu:false
            })
        }
        $('#tableProjekcije').show()
    }

    kupiKartu(){
        $('#idProjekcija').hide()
        this.setState({
            kupiKartu:true
        })
    }

    setValue(){
        this.setState({
            projekcija: "",
            kupiKartu:false
        })
    }

    render(){
        const prikaz=this.state.projekcije.map(p => <PrikazProjekcija handleClickFilm={this.handleClickFilm} handleClick={this.handleClick} key={p.id} projekcija={p} />)
        const prikazProjekcije=this.state.projekcija !== "" && <Projekcija kupiKartu={this.kupiKartu} state={this.props.state} cancel={this.cancel} editFilm={this.editProjekcija} deleteProjekcija={this.deleteProjekcija} brMesta={this.state.brMesta} projekcija={this.state.projekcija} />
        const prikazFilma=this.state.film !== "" && <Film state={this.state} cancel={this.cancel} editFilm={this.editFilm} deleteFilm={this.deleteFilm} film={this.state.film} />
        const kupiKartu=this.state.kupiKartu === true && <KupovinaKarte setValue={this.setValue} cancel={this.cancel} parentState = {this.props.state} state={this.state}/>

        return(
            <div id="idProjekcije">
                <div id="tableProjekcije">
                    <label>
                        Sortiraj po:
                            <select id="idSortProjekcije" onChange={this.filter}>
                                <option value=""></option>
                                <option value="f-asc">Filmu, rastuce</option>
                                <option value="f-desc">Filmu, opadajuce</option>
                                <option value="v-asc">Vremenu prikazivanja, rastuce</option>
                                <option value="v-desc">Vremenu prikazivanja, opadajuce</option>
                                <option value="t-asc">Tipu projekcije, rastuce</option>
                                <option value="t-desc">Tipu projekcije, opadajuce</option>
                                <option value="s-asc">Sali, rastuce</option>
                                <option value="s-desc">Sali, opadajuce</option>
                                <option value="c-asc">Ceni karte, rastuce</option>
                                <option value="c-desc">Ceni karte, opadajuce</option>
                            </select>
                    </label>
                    <table border="1">
                        <FilterProjekcija state={this.state} filter={this.filter} />
                        <tbody>
                            {prikaz}
                        </tbody>
                    </table>
                </div>
                {prikazProjekcije}
                {prikazFilma}
                {kupiKartu}
            </div>
        )
    }
}

export default Projekcije