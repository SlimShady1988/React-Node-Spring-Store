const uuid = require('uuid');
const path = require('path');
const {Device} = require("../models/models");
const {DeviceInfo} = require("../models/models");
const ApiError = require("../error/ApiError")



class DeviceController{
    async create(req, resp, next) {
        try {
            const {img} = req.files;
            let fileName = uuid.v4() + ".jpg"
            await img.mv(path.resolve(__dirname, "..", "static", fileName));
            req.body.img = fileName;
            const device = await Device.create(req.body);

            if (req.body.info){
                req.body.inf = JSON.parse(req.body.info)
                req.body.inf.forEach(e =>
                    DeviceInfo.create( {
                        title: e.title,
                        description: e.description,
                        deviceId: device.id,
                    })
                )
            }

            return resp.json(device);
        } catch (e) {
            next(ApiError.badRequest(e.message));
        }
    }

    async getAll(req, resp) {
        let {brandId, typeId, limit, page} = req.query;
        limit = limit || 9;
        page = page || 1;
        let offset = page*limit - limit
        let devices;
        if (!brandId && !typeId) {
            devices = await Device.findAndCountAll({limit, offset})
        }
        if (brandId && !typeId) {
            devices = await Device.findAndCountAll({where: {brandId}, limit, offset})
        }
        if (!brandId && typeId) {
            devices = await Device.findAndCountAll({where: {typeId}, limit, offset})
        }
        if (brandId && typeId) {
            devices = await Device.findAndCountAll({where: {typeId, brandId}, limit, offset})
        }

        return resp.json(devices);
    }

    async getOne(req, resp) {
        const {id} = req.params;
        const device = await Device.findOne({
            where: {id},
            include: [{model:DeviceInfo, as: "info"}]
        });

        return resp.json(device);
    }

}

module.exports = new DeviceController();