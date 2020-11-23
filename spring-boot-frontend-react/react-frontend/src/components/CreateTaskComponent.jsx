

import React, { Component } from 'react'
import TaskService from '../services/TaskService';
import DatePicker from "react-datepicker";
import moment from 'moment';
 
import "react-datepicker/dist/react-datepicker.css";

class CreateTaskComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            descripcion: '',
            fechaCreacion: new Date(),
            vigencia: ''
        }
        this.changeDescripcionHandler = this.changeDescripcionHandler.bind(this);
        this.changeFechaCreacionHandler = this.changeFechaCreacionHandler.bind(this);
        this.saveOrUpdateTask = this.saveOrUpdateTask.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            TaskService.getTaskById(this.state.id).then( (res) =>{
                let task = res.data;
                this.setState({descripcion: task.descripcion,
                    fechaCreacion: task.fechaCreacion,
                    vigencia : task.vigencia
                });
            });
        }        
    }
    saveOrUpdateTask = (e) => {
        e.preventDefault();
        let task = {descripcion: this.state.descripcion, fechaCreacion: this.state.fechaCreacion, vigencia: this.state.vigencia};
        console.log('task => ' + JSON.stringify(task));

        // step 5
        if(this.state.id === '_add'){
            TaskService.createTask(task).then(res =>{
                this.props.history.push('/tasks');
            });
        }else{
            TaskService.updateTask(task, this.state.id).then( res => {
                this.props.history.push('/tasks');
            });
        }
    }
    
    changeDescripcionHandler= (event) => {
        this.setState({descripcion: event.target.value});
    }

    changeFechaCreacionHandler= (event) => {
        this.setState({fechaCreacion: moment.format(event.target.value).format('YYYY-MM-DD')});
    }

    changeVigenciaHandler= (event) => {
        this.setState({vigencia: event.target.value});
    }

    cancel(){
        this.props.history.push('/tasks');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Task</h3>
        }else{
            return <h3 className="text-center">Update Task</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Descripcion: </label>
                                            <input placeholder="Descripcion" name="descripcion" className="form-control" 
                                                value={this.state.descripcion} onChange={this.changeDescripcionHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Fecha Creacion: </label>
                                            <input placeholder="Fecha Creacion" name="fechaCreacion" className="form-control" 
                                                value={this.state.fechaCreacion} onChange={this.changeFechaCreacionHandler}/>
                                            
                                            
                                        </div>
                                        <div className = "form-group">
                                            <label> Vigencia: </label>
                                            <input placeholder="Vigencia" name="vigencia" className="form-control" 
                                                value={this.state.vigencia} onChange={this.changeVigenciaHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateTask}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateTaskComponent

