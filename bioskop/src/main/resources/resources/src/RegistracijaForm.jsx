import React from "react"

function RegistracijaForm(props){
    return(
        <form onSubmit={props.handleSubmit}>
                <input 
                    type="text" 
                    placeholder="Korisnicko ime..." 
                    name="korisnickoIme" 
                    value={props.state.korisnickoIme} 
                    onChange={props.handleChange} 
                />
                <br/>
                <input 
                    type="password" 
                    placeholder="Lozinka..." 
                    name="lozinka" 
                    value={props.state.lozinka} 
                    onChange={props.handleChange} 
                />
                <br/>
                <button type="submit">Registruj se</button>
                <button onClick={props.handleClick} type="button">Prijava</button>
            </form>
    )
}

export default RegistracijaForm