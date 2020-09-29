import React from "react"
import FormComponent from "./FormComponent"

class Form extends React.Component{

    constructor(){
        super()
        this.state = {
            firstName: "",
            lastName: "",
            age: "",
            gender: "",
            destination: "",
            dietaryRestriction: {
                isVegan: false,
                isKosher: false,
                isLactoseFree: false
            }
        }
        this.handleChange=this.handleChange.bind(this)
    }

    handleChange(event){
        const {name, value, type, checked} = event.target
        type === "checkbox" ? 
        this.setState({[name]: checked})
        :this.setState({[name]: value})
    }

    render(){
        return(
            <FormComponent state={this.state} handleChange={this.handleChange} />
        )
    }

}

export default Form