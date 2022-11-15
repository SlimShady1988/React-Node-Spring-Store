require('dotenv').config();
const express = require('express');
const fileUpload = require('express-fileupload');
const cors = require('cors');
const path = require('path');
const sequelize = require('./db');
const router = require('./routes/index');
const errorHandler = require("./middleware/ErrorHandlingMiddleware")

const PORT = process.env.PORT;
const app = express();

console.log(__dirname)
app.use(cors());
app.use(express.json());
app.use(express.static(path.resolve(__dirname, 'static')));
app.use(fileUpload({}));
app.use('/api', router);




//Обов'язково останній має бути
app.use(errorHandler);

// app.get(
//     "/",
//     (req, res) => {res.status(200).json({message: "WORKING!!!"})}
// )


const start = async () => {
    try {
        await sequelize.authenticate();
        await sequelize.sync();
        app.listen(PORT, () => console.log());
    } catch (e) {
        console.log(e)
    }
}

start();