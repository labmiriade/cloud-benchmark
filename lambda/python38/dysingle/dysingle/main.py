import json
import os
from typing import Dict

import boto3

dynamodb = boto3.resource('dynamodb')
tablename = os.environ['MainTable']
table = dynamodb.Table(tablename)


def handler(event: Dict, _):
    key = event['pathParameters']['key']
    res = table.get_item(
        Key={
            'pk': key,
            'sk': key,
        },
    )
    item = res.get('Item')
    if item is None:
        response = {
            'statusCode': 404,
            'body': json.dumps({
                'message': f'Item "{key}" does not exist'
            })
        }
    else:
        response = {
            'statusCode': 200,
            'body': json.dumps(item)
        }
    return response
