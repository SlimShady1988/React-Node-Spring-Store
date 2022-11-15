const ApiError = require("../error/ApiError")
const bcrypt = require("bcrypt")
const {User, Basket} = require("../models/models")
const jwt = require("jsonwebtoken");

function generateJwt(userId, email, role) {
    return jwt.sign(
        {id: userId, email, role},
        process.env.SECRET_KEY,
        {expiresIn:"24h"}
    );
}

class UserController{
    async registration(req, resp, next) {
        const {email, password, role} = req.body;
        if (!email && !password) {
            return next(ApiError.badRequest("WRONG EMAIL OR PASSWORD"));
        }
        const candidate = await User.findOne({where: {email} });
        if (candidate) {
            return next(ApiError.badRequest("User with this email has existed"));
        }
        const hashPass = await bcrypt.hash(password, 5);
        const user = await User.create({email, role, password: hashPass});
        const basket = await Basket.create({userId: user.id})
        const token = generateJwt(user.id, user.email, user.role)

        return resp.json({token})

    }
    async login(req, resp, next) {
        const {email, password} = req.body;
        const user = await User.findOne({where: {email}});
        if (!user) {
            return next(ApiError.internal("User has not found"))
        }
        let comparePass = bcrypt.compareSync(password, user.password);

        if (!comparePass) {
            return next(ApiError.internal("Wrong password"))
        }

        const token = generateJwt(user.id, user.email, user.role)

        return resp.json({token});

    }

    async check(req, resp) {
        const token = generateJwt(req.user.id, req.user.email, req.user.role)
        resp.json({token});
    }
}

module.exports = new UserController();