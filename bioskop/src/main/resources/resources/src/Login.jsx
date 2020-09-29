import React from "react"

function Login(props){
    return(
        <div id="idLogin">
            <form onSubmit={props.handleSubmit}>
                <input name="usernameRequest" type="text" placeholder="Korisnicko..." value={props.state.usernameRequest} onChange={props.handleChange}/>
                <br />
                <input name="passwordRequest" type="password" placeholder="Lozinka..." value={props.state.passwordRequest} onChange={props.handleChange}/>
                <br />
                <button type="submit">Prijavi se</button>
                <button type="button" id="registracija" name="Registracija" onClick={props.handleClick}>Registracija</button>
            </form>
        </div>
    )
}

export default Login