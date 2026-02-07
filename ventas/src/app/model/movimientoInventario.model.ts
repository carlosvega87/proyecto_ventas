import { Productos } from "./productos.model"
export interface MovimientoInventario{
    idMovimiento:number
    tipo:string
    cantidad:number
    fechaMovimiento:string
   productos : Productos
   [key :string]:any;
        
}
