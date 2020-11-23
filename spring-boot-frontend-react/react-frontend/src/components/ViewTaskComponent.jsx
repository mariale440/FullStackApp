

import React, { Component } from 'react'
import TaskService from '../services/TaskService'

class ViewTaskComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            task: {}
        }
    }

    componentDidMount(){
        TaskService.getTaskById(this.state.id).then( res => {
            this.setState({task: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> Editar Tarea</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Descripcion: </label>
                            <div> { this.state.task.descripcion }</div>
                        </div>
                        <div className = "row">
                            <label> Fecha creacion: </label>
                            <div> { this.state.task.fechaCreacion }</div>
                        </div>
                        <div className = "row">
                            <label> Vigencia: </label>
                            <div> { this.state.task.vigencia }</div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewTaskComponent

