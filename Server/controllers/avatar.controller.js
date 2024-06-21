const Avatar = require("../models/avatar.model");

const controller = {};

controller.findAll = async (req, res, next)=>{
    try {
       const avatar = await Avatar.find()
       return res.status(200).json({avatar});
    } catch (error) {

        next(error);
    }
}
controller.save = async (req, res, next)=>{
    try {
        const { costo, nombre, imagen} = req.body;


        let avatar = await Avatar.find({imagen: imagen});

        if(avatar.length===0){
            avatar = new Avatar();
        }else{
            return res.status(409).json({error: "El avatar ya existe"});
        }

        avatar["costo"]=costo;
        avatar["nombre"]=nombre;
        avatar["imagen"]=imagen;

       const avatarSaved = await avatar.save();

       if(!avatarSaved){
        return res.status(409).json({error: "Error creating lesson"});
       }
       return res.status(201).json(avatarSaved);
    } catch (error) {

        next(error);
    }
}

    module.exports = controller;