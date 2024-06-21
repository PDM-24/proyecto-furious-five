const {body} = require("express-validator");
const validators = {};

validators.createAvatarValidator = [
body("costo")
.isInt({ min: 0 }).withMessage("EL costo tiene que ser un numero entero positivo"),
body("nombre")
.notEmpty().withMessage("Se requiere colocar un nombre")
.isLength({max: 30}).withMessage("El maximo numero de caracteres del nombre es 30"),
body("imagen")
.notEmpty().withMessage("Se requiere colocar una imagen")
.isURL().withMessage("La imagen tiene que ser una url")
];
module.exports = validators;