const { Schema, model } = require("mongoose");
const avatarSchema = new Schema(
    {
      nombre: {
        type: String,
        required: true,
        trim: true
      },
      imagen: {
        type: String,
        required: true
      },
      costo: {
        type: Number,
        required: true
      }
    },
    {
      timestamps: true,
    }
  );
  module.exports = model("Avatar", avatarSchema);