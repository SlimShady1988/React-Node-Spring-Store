const Router = require("express");
const userController = require("../controllers/UserController");
const router = new Router();
const authMiddleware = require("../middleware/AuthMiddleware");


router.post("/registration", userController.registration)
router.post("/login", userController.login)
router.get("/auth", authMiddleware, userController.check)

module.exports = router;