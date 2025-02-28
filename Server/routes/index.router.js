const express = require("express");
const router = express.Router();

const lessonRouter = require("./lesson.router");

const authRouter = require("./auth.router");
const temaRouter = require("./tema.router");
const examRouter = require("./exam.router");
const avatarRouter = require("./avatar.router");


router.use("/lesson", lessonRouter);
router.use("/auth", authRouter);
router.use("/tema", temaRouter);
router.use("/exam", examRouter);
router.use("/avatar",avatarRouter);



module.exports = router;