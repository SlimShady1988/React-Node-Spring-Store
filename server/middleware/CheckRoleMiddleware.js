const jwt = require("jsonwebtoken");

module.exports = function (role) {
    return function (req, resp, next) {
        if (req.method === "OPTIONS") {
            next()
        }
        try {
            const token = req.headers.authorization.split(" ")[1];
            if (!token) {
                return  resp.status(401).json({message: " You have not authorised yet"});
            }

            const decoded = jwt.verify(token, process.env.SECRET_KEY);

            if (decoded.role !== role) {
                return resp.status(403).json({message: " You have no access"});
            }
            req.user = decoded;
            next();

        } catch (e) {
            resp.status(401).json({message: " You have not authorised yet"});
        }
    }
}