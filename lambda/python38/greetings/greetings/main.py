import json


def handler(event, _):
    path_parameters = event.get("pathParameters") or {}
    name = path_parameters.get("name", "world")
    message = f"Hello, {name}!"
    return {
        "statusCode": 200,
        "body": json.dumps({"message": message}),
    }
