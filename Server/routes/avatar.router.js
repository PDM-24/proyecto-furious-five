const express = require("express");
const router = express.Router();


const Roles = require("../data/roles.constant.json");

const {createAvatarValidator} = require("../validators/avatar.validator");
const validateFields = require("../validators/index.middleware");


const {authentication, authorization} = require("../middlewares/auth.middlewares")

const avatarController = require("../controllers/avatar.controller");
 

router.get("/", authentication,authorization(Roles.USER),avatarController.findAll);


router.post("/", authentication, authorization(Roles.ADMIN),createAvatarValidator, validateFields ,avatarController.save);



module.exports = router;