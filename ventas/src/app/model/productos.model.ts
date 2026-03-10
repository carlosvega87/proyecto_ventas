import { Categoria } from "./categoria.model"
export interface Productos{
    idProducto:number
    nombre:string
    precio:number
    stock:number
    fechaRegistro:string
    estado: string
   categoria: Categoria
   [key :string]:any;
        
}
