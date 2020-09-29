import React from "react"

function ContactCard(props){
    console.log(props)
    return(
        <div>
            <img src={props.contact.url} />
            <h3>{props.contact.name}</h3>
            <p>Phone: {props.contact.phone}</p>
            <p>Email: {props.contact.email}</p>
        </div>
    )
}

export default ContactCard