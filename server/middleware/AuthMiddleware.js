const jwt = require("jsonwebtoken");

module.exports = function (req, resp, next) {

    if (req.method ==="OPTIONS") {
        next()
    }
    try {
        const token = req.headers.authorization.split(" ")[1];
        if (!token) {
           return  resp.status(401).json({message: " You have not authorised yet"});
        }

        req.user = jwt.verify(token, process.env.SECRET_KEY);
        next();

    } catch (e) {
        resp.status(401).json({message: " You have not authorised yet"});
    }
}